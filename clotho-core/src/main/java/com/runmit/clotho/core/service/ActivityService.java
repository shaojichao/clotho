package com.runmit.clotho.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.runmit.clotho.core.domain.activity.Activity;
import com.runmit.clotho.core.domain.activity.ActivityGift;
import com.runmit.clotho.core.domain.activity.ActivityRecord;
import com.runmit.clotho.core.mapper.ActivityMapper;
import com.runmit.clotho.core.util.DateUtils;
import com.runmit.clotho.log.domain.OpLog.OpType;
import com.runmit.clotho.log.service.OpLogService;

/**
 * @author zhipeng.tian
 * 
 * 2014年12月2日
 */
@Service
@Transactional(readOnly=true)
public class ActivityService {

	@Autowired
	private ActivityMapper activityMapper;
	@Reference(version="1.0.0")
	private OpLogService opLogService;
	
	public List<Activity> getActivityList(int start,int limit,int status){
		return this.activityMapper.getActivityList(start, limit, status);
	}
	
	public long getActivityCount(int status){
		return this.activityMapper.getActivityCount(status);
	}
	
	public Activity getActiveActivityById(int id){
		return this.activityMapper.getActiveActivityById(id);
	}
	
	public List<ActivityRecord> getRecord(int start,int limit,int activityId){
		return this.activityMapper.getRecord(start, limit, activityId);
	}
	
	public long getRecordCount(int activityId){
		return this.activityMapper.getRecordCount(activityId);
	}
	
	@Transactional(readOnly=false)
	public void saveOrUpdateAcitivity(Activity activity) throws Exception{
		if(!StringUtils.isEmpty(activity.getDateBeginStr())){
			activity.setDateBegin(DateUtils.parseDateTime(activity.getDateBeginStr()));
		}
		if(!StringUtils.isEmpty(activity.getDateEndStr())){
			activity.setDateEnd(DateUtils.parseDateTime(activity.getDateEndStr()));
		}
		if(activity.getId()==null||activity.getId()==0){
			this.activityMapper.saveActivity(activity);
			this.opLogService.saveObj(activity, OpType.INSERT, "activity", "clotho", activity.getCreateby());
		}else{
			Object temp = this.activityMapper.getActivityById(activity.getId());
			this.activityMapper.updateActivity(activity);
			this.opLogService.updateObj(temp, activity, OpType.UPDATE, "activity", "clotho", activity.getUpdateby());
		}
	}
	
	public List<ActivityGift> getActivityGiftList(int activityId){
		return this.activityMapper.getActivityGiftList(activityId);
	}
	
	@Transactional(readOnly=false)
	public void deleteGift(int id,String adminName){
		this.opLogService.saveObj(this.activityMapper.getActivityGift(id), OpType.DELETE, "activity-gift", "clotho", adminName);
		this.activityMapper.deleteGift(id);
	}
	
	@Transactional(readOnly=false)
	public void saveGift(ActivityGift gift){
		this.activityMapper.saveGift(gift);
		this.opLogService.saveObj(gift, OpType.INSERT, "activity-gift", "clotho", gift.getAdminName());
	}
	
	public long getRecordCountByUid(int activityId,int uid){
		return this.activityMapper.getRecordCountByUid(activityId, uid);
	}
	
	@Transactional(readOnly=false)
	public void saveActivityRecord(ActivityRecord record){
		this.activityMapper.saveActivityRecord(record);
	}
}
