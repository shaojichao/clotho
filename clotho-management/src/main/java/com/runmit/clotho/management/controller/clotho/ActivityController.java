package com.runmit.clotho.management.controller.clotho;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runmit.clotho.core.domain.activity.Activity;
import com.runmit.clotho.core.domain.activity.ActivityGift;
import com.runmit.clotho.core.domain.activity.ActivityRecord;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.ActivityService;
import com.runmit.clotho.management.security.SessionUtil;

/**
 * @author zhipeng.tian
 * 
 * 2014年12月2日
 */
@Controller
@RequestMapping(value = "/clotho/activity")
public class ActivityController {
	private static final Logger LOGGER = LoggerFactory
            .getLogger(ActivityController.class);
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(value = "/list.do")
	public @ResponseBody ExtEntity<Activity> getActivities(
			@RequestParam(value = "start", required = false,defaultValue="0") Integer start,
            @RequestParam(value = "limit", required = false,defaultValue="20") Integer limit,
            @RequestParam(value = "status", required = false,defaultValue="-1") Integer status) {
		
		ExtEntity<Activity> listdata = new ExtEntity<Activity>();
		List<Activity> list = this.activityService.getActivityList(start, limit, status);
		listdata.setRows(list);
		listdata.setResult(activityService.getActivityCount(status));
		
		LOGGER.info("getActivities");
		return listdata;
	}
	
	@RequestMapping(value = "/saveActivity.do")
	public @ResponseBody ExtStatusEntity saveActivity(Activity activity,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		
		if(activity.getId()==null||activity.getId()==0){
			activity.setCreateby(SessionUtil.getLoginAdminName(request));
		}else{
			activity.setUpdateby(SessionUtil.getLoginAdminName(request));
		}
		try{
			this.activityService.saveOrUpdateAcitivity(activity);;
			
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("saveActivity error",ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}
		
		LOGGER.info("saveActivity");
		return entity;
	}
	
	@RequestMapping(value = "/giftlist.do")
	public @ResponseBody ExtEntity<ActivityGift> getActivityGiftList(@RequestParam(value = "activityId") Integer activityId) {
		
		ExtEntity<ActivityGift> listdata = new ExtEntity<ActivityGift>();
		List<ActivityGift> list = this.activityService.getActivityGiftList(activityId);
		listdata.setRows(list);
		listdata.setResult(list.size());
		
		LOGGER.info("getActivityGiftList");
		return listdata;
	}
	
	@RequestMapping(value = "/saveGift.do")
	public @ResponseBody ExtStatusEntity saveGift(ActivityGift gift,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		
		try{
			gift.setAdminName(SessionUtil.getLoginAdminName(request));
			this.activityService.saveGift(gift);
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("saveGift error",ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}
		LOGGER.info("saveGift");
		return entity;
	}
	
	@RequestMapping(value = "/delGift.do")
	public @ResponseBody ExtStatusEntity delGift(@RequestParam(value = "id") Integer id,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		try{
			this.activityService.deleteGift(id,SessionUtil.getLoginAdminName(request));
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("delGift error",ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}
		LOGGER.info("delGift");
		return entity;
	}
	
	@RequestMapping(value = "/recordlist.do")
	public @ResponseBody ExtEntity<ActivityRecord> getRecord(
			@RequestParam(value = "start", required = false,defaultValue="0") Integer start,
            @RequestParam(value = "limit", required = false,defaultValue="20") Integer limit,
            @RequestParam(value = "activityId", required = false,defaultValue="0") Integer activityId) {
		
		ExtEntity<ActivityRecord> listdata = new ExtEntity<ActivityRecord>();
		List<ActivityRecord> list = this.activityService.getRecord(start, limit, activityId);
		listdata.setRows(list);
		listdata.setResult(activityService.getRecordCount(activityId));
		
		LOGGER.info("getRecord");
		return listdata;
	}
}
