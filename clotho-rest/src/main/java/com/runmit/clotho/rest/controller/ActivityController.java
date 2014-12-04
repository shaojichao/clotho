package com.runmit.clotho.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.runmit.clotho.core.domain.activity.Activity;
import com.runmit.clotho.core.domain.activity.ActivityRecord;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.service.ActivityService;
import com.runmit.clotho.rest.common.RestConst;
import com.runmit.clotho.rest.domain.CommonResp;

/**
 * @author zhipeng.tian
 * 
 * 2014年12月4日
 */
@RestController
@RequestMapping(value = "/activity")
public class ActivityController {
	private static final Logger LOGGER = LoggerFactory
            .getLogger(ActivityController.class);

    @Autowired
    private ActivityService activityService;
    
    @RequestMapping(value = "/list.do")
	public @ResponseBody ExtEntity<Activity> getActivities(
			@RequestParam(value = "start", required = false,defaultValue="0") Integer start,
            @RequestParam(value = "limit", required = false,defaultValue="20") Integer limit) {
		
		ExtEntity<Activity> listdata = new ExtEntity<Activity>();
		List<Activity> list = this.activityService.getActivityList(start, limit, 3);
		listdata.setRows(list);
		listdata.setResult(activityService.getActivityCount(3));
		
		LOGGER.info("getActivities");
		return listdata;
	}
    
    @RequestMapping(value = "/join.do")
	public ResponseEntity<CommonResp> joinActivity(@RequestParam("activityId") Integer activityId,
			@RequestParam("userId") Integer userId) {
    	CommonResp cr=new CommonResp();
    	
    	Activity activity = this.activityService.getActiveActivityById(activityId);
    	if(null == activity){
    		cr.setRtn(RestConst.RTN_ACTIVITY_NOTEXIST);
    		cr.setRtmsg("活动不存在或者已过期,请重新选择.");
    		return new ResponseEntity<>(cr, HttpStatus.OK);
    	}else{
    		int timeLimit = activity.getTimeLimit();
    		if(timeLimit!=0){
    			long times = this.activityService.getRecordCountByUid(activityId, userId);
    			if(timeLimit<=times){
    				cr.setRtn(RestConst.RTN_ACTIVITY_HADJOINED);
    				cr.setRtmsg("您已经参加过该活动.");
    				return new ResponseEntity<>(cr, HttpStatus.OK);
    			}
    		}
    		int scoreLimit = activity.getScoreLimit();
    		if(scoreLimit!=0){
    			int userScore = 100;//todo get from hades;
    			if(scoreLimit>userScore){
    				cr.setRtn(RestConst.RTN_ACTIVITY_NOTENOUGHSCORE);
    				cr.setRtmsg("积分不足");
    				return new ResponseEntity<>(cr, HttpStatus.OK);
    			}
    		}
    		//join activity
    		
    		ActivityRecord record = new ActivityRecord();
    		record.setActivityId(activityId);
    		record.setUid(userId);
    		record.setUsername("test");
    		this.activityService.saveActivityRecord(record);
    		
    		cr.setRtn(RestConst.RTN_OK);
    		cr.setRtmsg("操作成功");
    		return new ResponseEntity<>(cr, HttpStatus.OK);
    	}
	}
}
