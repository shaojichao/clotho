package com.runmit.clotho.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.runmit.clotho.core.domain.activity.Activity;
import com.runmit.clotho.core.domain.activity.ActivityGift;
import com.runmit.clotho.core.domain.activity.ActivityRecord;

/**
 * @author zhipeng.tian
 * 
 * 2014年12月2日
 */

public interface ActivityMapper {
	
	@Insert("INSERT INTO Activity (`name`,`content`,`status`,`dateBegin`,`dateEnd`,`timeLimit`,`scoreLimit`,`createby`) "
			+ "values (#{name},#{content},#{status},#{dateBegin},#{dateEnd},#{timeLimit},#{scoreLimit},#{createby})")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void saveActivity(Activity activity);
	
	@Update("update Activity set name=#{name},content=#{content},status=#{status},dateBegin=#{dateBegin},dateEnd=#{dateEnd},"
			+ "timeLimit=#{timeLimit},scoreLimit=#{scoreLimit},updateby=#{updateby},updatetime=now() where id=#{id}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void updateActivity(Activity activity);
	
	@Select("select * from Activity where id=#{id}")
	Activity getActivityById(@Param("id")int id);
	
	@Select("select * from Activity where id=#{id} and (dateBegin is null or dateBegin<=now()) and (dateEnd is null or dateEnd>= now())")
	Activity getActiveActivityById(@Param("id")int id);
	
	@SelectProvider(type=GetActivities.class,method="getActivityList")
	List<Activity> getActivityList(@Param("start")int start,@Param("limit")int limit,@Param("status")int status);
	
	@SelectProvider(type=GetActivities.class,method="getActivityCount")
	long getActivityCount(@Param("status")int status);
	
	@SelectProvider(type=GetActivities.class,method="getRecord")
	List<ActivityRecord> getRecord(@Param("start")int start,@Param("limit")int limit,@Param("activityId")int activityId);
	
	@SelectProvider(type=GetActivities.class,method="getRecordCount")
	long getRecordCount(@Param("activityId")int activityId);
	 
	 class GetActivities{
		 public String getActivityList(Map<String, Object> para){
			 StringBuilder sb = new StringBuilder("select * from Activity where id!=0");
			 int status = (Integer)para.get("status");
			 if(status != -1){
				 if(status == 0){//无效
					 sb.append(" and status=0 ");
				 }else if(status == 1){//有效
					 sb.append(" and status=1 ");
				 }else{//当前有效
					 sb.append(" and status=1 and (dateBegin is null or dateBegin<=now()) and (dateEnd is null or dateEnd>= now()) ");
				 }
				 
			 }
			 sb.append(" order by id desc ");
			 sb.append(" limit ").append(para.get("start")).append(",").append((Integer)para.get("limit"));
			 return sb.toString();
		 }
		 
		 public String getActivityCount(Map<String, Object> para){
			 StringBuilder sb = new StringBuilder("select count(id) from Activity where id!=0");
			 int status = (Integer)para.get("status");
			 if(status != -1){
				 if(status == 0){//无效
					 sb.append(" and status=0 ");
				 }else if(status == 1){//有效
					 sb.append(" and status=1 ");
				 }else{//当前有效
					 sb.append(" and status=1 and (dateBegin is null or dateBegin<=now()) and (dateEnd is null or dateEnd>= now()) ");
				 }
				 
			 }
			 return sb.toString();
		 }
		 
		 public String getRecord(Map<String, Object> para){
			 StringBuilder sb = new StringBuilder("select a.*,b.name as activityName from ActivityRecord a left join Activity b on a.activityId = b.id where a.id!=0");
			 int activityId = (Integer)para.get("activityId");
			 if(activityId != 0){
				 sb.append(" and a.activityId=").append(activityId);
				 
			 }
			 sb.append(" order by a.id desc ");
			 sb.append(" limit ").append(para.get("start")).append(",").append((Integer)para.get("limit"));
			 return sb.toString();
		 }
		 
		 public String getRecordCount(Map<String, Object> para){
			 StringBuilder sb = new StringBuilder("select count(id) from ActivityRecord where id!=0");
			 int activityId = (Integer)para.get("activityId");
			 if(activityId != 0){
				 sb.append(" and activityId=").append(activityId);
				 
			 }
			 return sb.toString();
		 }
	 }
	 
	@Select("select * from ActivityGift where activityId=#{activityId}")
	List<ActivityGift> getActivityGiftList(@Param("activityId")int activityId);
	
	@Delete("delete from ActivityGift where id=#{id}")
	void deleteGift(@Param("id")int id);
	
	@Insert("insert into ActivityGift (activityId,giftId,giftName,giftNum)"
			+ " values (#{activityId},#{giftId},#{giftName},#{giftNum})")
	void saveGift(ActivityGift gift);
	
	@Select("select * from ActivityGift where id=#{id}")
	ActivityGift getActivityGift(@Param("id")int id);
	
	@Select("select count(id) from ActivityRecord where activityId=#{activityId} and uid=#{uid}")
	long getRecordCountByUid(@Param("activityId")int activityId,@Param("uid")int uid);
	
	@Insert("insert into ActivityRecord (activityId,uid,username)"
			+ " values (#{activityId},#{uid},#{username})")
	void saveActivityRecord(ActivityRecord record);
}
