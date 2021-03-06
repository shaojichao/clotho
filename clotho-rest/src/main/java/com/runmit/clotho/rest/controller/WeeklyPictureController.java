package com.runmit.clotho.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.runmit.clotho.core.domain.picture.WeeklyPicture;
import com.runmit.clotho.core.service.WeeklyPictureService;
import com.runmit.clotho.rest.common.RestConst;
import com.runmit.clotho.rest.domain.CommonResp;
import com.runmit.clotho.rest.domain.RespWeeklyPicture;

/**
 * @author hongbin.cao
 *
 * @date 2015年4月7日
 */
@RestController
@RequestMapping(value = "/weeklyPicture")
public class WeeklyPictureController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeeklyPictureController.class);

    @Autowired
    private WeeklyPictureService weeklyPictureService;
    
    /**
     * get weeklyPicture list
     * @return
     */
    @RequestMapping(value = "/getPictureWeekly")
    public CommonResp getPictureWeekly(@RequestParam(value = "lang", required = false, defaultValue = "zh") String lang){
    	CommonResp resp = new CommonResp();
    	try{
    		WeeklyPicture pictureWeekly = this.weeklyPictureService.getPictureWeekly(lang);
    		if(null == pictureWeekly){
    			pictureWeekly = this.weeklyPictureService.getPictureWeekly("en");
    		}
            RespWeeklyPicture rep = new RespWeeklyPicture();
            /* 属性重组 */
            BeanUtils.copyProperties(pictureWeekly, rep);
            rep.setId(pictureWeekly.getId().toString());
    		resp.setData(rep);
    		resp.setRtn(RestConst.RTN_OK);
            resp.setRtmsg("操作成功");
            LOGGER.info("getPictureList succeed");
    	}catch(Exception ex){
    		LOGGER.error("getPictureList error", ex);
    		resp.setRtn(RestConst.RTN_ERROR);
            resp.setRtmsg("操作失败");
    	}
    	return resp;
    }
    
    /**
     * get weeklyPicture list
     * @return
     */
    @RequestMapping(value = "/getControllerPicture")
    public CommonResp getControllerPicture(@RequestParam(value = "lang", required = false, defaultValue = "zh") String lang){
    	CommonResp resp = new CommonResp();
    	try{
    		List<WeeklyPicture> pictures = this.weeklyPictureService.getWeeklyPictureByType(3,lang);
    		if(null == pictures){
    			pictures = this.weeklyPictureService.getWeeklyPictureByType(3,"en");
    		}
            List<RespWeeklyPicture> reps = new ArrayList<RespWeeklyPicture>();
            /* 属性重组 */
            for(WeeklyPicture p:pictures){
            	RespWeeklyPicture pic = new RespWeeklyPicture();
            	BeanUtils.copyProperties(p, pic);
            	pic.setId(p.getId().toString());
            	reps.add(pic);
            }
    		resp.setData(reps);
    		resp.setRtn(RestConst.RTN_OK);
            resp.setRtmsg("操作成功");
            LOGGER.info("getPictureList succeed");
    	}catch(Exception ex){
    		LOGGER.error("getPictureList error", ex);
    		resp.setRtn(RestConst.RTN_ERROR);
            resp.setRtmsg("操作失败");
    	}
    	return resp;
    }
}
