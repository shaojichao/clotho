package com.runmit.clotho.management.controller.clotho;

import com.runmit.clotho.core.domain.CountryCode;
import com.runmit.clotho.core.domain.picture.WeeklyPicture;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.CountryCodeService;
import com.runmit.clotho.core.service.WeeklyPictureService;
import com.runmit.clotho.management.security.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping(value = "/allList.do")
	public @ResponseBody ExtEntity<WeeklyPicture> getWeeklyPictures() {
		
		List<WeeklyPicture> list = this.weeklyPictureService.getList();
		ExtEntity<WeeklyPicture> entity = new ExtEntity<WeeklyPicture>();
		entity.setResult(list.size());
		entity.setRows(list);
		return entity;
	}
	
	@RequestMapping(value = "/save.do")
	public @ResponseBody ExtStatusEntity saveWeeklyPicture(WeeklyPicture weeklyPicture,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		
		if(weeklyPicture.getId()==null||weeklyPicture.getId()==0){
            weeklyPicture.setCreateby(SessionUtil.getLoginAdminName(request));
		}else{
            weeklyPicture.setUpdateby(SessionUtil.getLoginAdminName(request));
		}
		try{
			this.weeklyPictureService.save(weeklyPicture);
			
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("saveWeeklyPicture error",ex);
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

}
