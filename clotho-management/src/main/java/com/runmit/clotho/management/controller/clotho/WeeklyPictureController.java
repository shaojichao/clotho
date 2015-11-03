package com.runmit.clotho.management.controller.clotho;

import com.runmit.clotho.core.domain.picture.WeeklyPicture;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.WeeklyPictureService;
import com.runmit.clotho.management.security.SessionUtil;
import com.runmit.clotho.management.service.CDNService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author hongbin.cao
 *
 * @date 2015年4月7日
 */
@Controller
@RequestMapping(value = "/clotho/weeklyPicture")
public class WeeklyPictureController {
	private static final Logger LOGGER = LoggerFactory.getLogger(WeeklyPictureController.class);
	
	@Autowired
	private WeeklyPictureService weeklyPictureService;
    @Autowired
    private CDNService cdnService;

	@RequestMapping(value = "/allList.do")
	public  @ResponseBody ExtEntity<WeeklyPicture> getWeeklyPictures(
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
            @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
            @RequestParam(value = "se_typeId", required = false) String type,
            @RequestParam(value = "se_lang", required = false) String lang,
            HttpServletRequest request) {
		List<WeeklyPicture> list = this.weeklyPictureService.getList(start, limit, StringUtils.isEmpty(type)?"1":type, StringUtils.isEmpty(lang)?null:lang);
		ExtEntity<WeeklyPicture> entity = new ExtEntity<WeeklyPicture>();
		entity.setResult(list.size());
		entity.setRows(list);
		return entity;
	}
	
	@RequestMapping(value = "/save.do")
	public @ResponseBody ExtStatusEntity saveWeeklyPicture(final WeeklyPicture weeklyPicture,HttpServletRequest request
            ,@RequestParam(value = "size", required = false, defaultValue = "0") Long size
            ,@RequestParam(value = "md5", required = false, defaultValue = "") String md5) {
		ExtStatusEntity entity = new ExtStatusEntity();
		
		if(weeklyPicture.getId()==null||weeklyPicture.getId()==0){
            weeklyPicture.setCreateby(SessionUtil.getLoginAdminName(request));
		}else{
            weeklyPicture.setUpdateby(SessionUtil.getLoginAdminName(request));
		}
		try{
			this.weeklyPictureService.save(weeklyPicture);
            final int vId = weeklyPicture.getId();
            //file dispatch
            LOGGER.info("size:{},md5:{}",size,md5);
            if(size!=null && size!=0 && !StringUtils.isEmpty(md5)){
                int res = -1;
                int tryTime = 2;
                for(int i=0;i<tryTime;i++){
                    res = this.cdnService.dispatchAppPic(weeklyPicture, md5, size);
                    if (res == 0) {
                        Thread t = new Thread() {
                            public void run() {
                                WeeklyPicture weeklyPictureT = weeklyPictureService.getPicture(vId);
                                if (weeklyPictureT.getDistributestatus() == 0) {
                                    // cdn dispatch distributing
                                    weeklyPictureT.setDistributestatus(1);
                                    weeklyPictureService.save(weeklyPictureT);
                                }
                            }
                        };
                        t.start();
                        break;
                    }
                }
                if(res!=0){
                    // cdn dispatch failed
                    weeklyPicture.setDistributestatus(2);
                    this.weeklyPictureService.save(weeklyPicture);
                    entity.setMsg("cdn分发失败");
                    entity.setSuccess(false);
                    return entity;
                }
            }
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("save WeeklyPicture error",ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}
		
		LOGGER.info("saveWeeklyPicture");
		return entity;
	}
	
	@RequestMapping(value = "/delete.do")
	public @ResponseBody ExtStatusEntity delete(@RequestParam("id")Integer id,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		try{
			this.weeklyPictureService.delete(id, SessionUtil.getLoginAdminName(request));
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("delete WeeklyPicture error",ex);
			entity.setMsg("删除失败");
			entity.setSuccess(false);
		}
		return entity;
	}

    @RequestMapping(value = "/distribute.do")
    public @ResponseBody ExtStatusEntity distribute(@RequestParam("id")Integer id,HttpServletRequest request) {
        ExtStatusEntity entity = new ExtStatusEntity();
        WeeklyPicture weeklyPicture = this.weeklyPictureService.getPicture(id);
        Long size = weeklyPicture.getFilesize();
        String md5 = weeklyPicture.getMd5();
        try{
            final int vId = weeklyPicture.getId();
            //file dispatch
            LOGGER.info("size:{},md5:{}",size,md5);
            if(size!=null && size!=0 && !StringUtils.isEmpty(md5)){
                int res = -1;
                int tryTime = 2;
                for(int i=0;i<tryTime;i++){
                    res = this.cdnService.dispatchAppPic(this.weeklyPictureService.getPicture(id), md5, size);
                    if (res == 0) {
                        Thread t = new Thread() {
                            public void run() {
                                WeeklyPicture weeklyPictureT = weeklyPictureService.getPicture(vId);
                                if (weeklyPictureT.getDistributestatus() == 0) {
                                    // cdn dispatch distributing
                                    weeklyPictureT.setDistributestatus(1);
                                    weeklyPictureService.save(weeklyPictureT);
                                }
                            }
                        };
                        t.start();
                        break;
                    }
                }
                if(res!=0){
                    // cdn dispatch failed
                    weeklyPicture.setDistributestatus(2);
                    this.weeklyPictureService.save(weeklyPicture);
                    entity.setMsg("cdn分发失败");
                    entity.setSuccess(false);
                    return entity;
                }
            }
        }catch(Exception ex){
            LOGGER.error("distribute WeeklyPicture error",ex);
            // cdn dispatch failed
            weeklyPicture.setDistributestatus(2);
            this.weeklyPictureService.save(weeklyPicture);
            entity.setMsg("cdn分发失败");
            entity.setSuccess(false);
        }
        return entity;
    }
    
    @RequestMapping(value = "/controllerList.do")
	public  @ResponseBody ExtEntity<WeeklyPicture> getControllerPictures(
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
            @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
            @RequestParam(value = "se_lang", required = false) String lang,
            HttpServletRequest request) {
		List<WeeklyPicture> list = this.weeklyPictureService.getList(start, limit, "3", StringUtils.isEmpty(lang)?null:lang);
		ExtEntity<WeeklyPicture> entity = new ExtEntity<WeeklyPicture>();
		entity.setResult(list.size());
		entity.setRows(list);
		return entity;
	}
}
