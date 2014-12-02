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
	
	@SelectProvider(type=GetActivities.class,method="getActivityList")
	List<Activity> getActivityList(@Param("start")int start,@Param("limit")int limit,@Param("status")int status);
	
	@SelectProvider(type=GetActivities.class,method="getActivityCount")
	long getActivityCount(@Param("status")int status);
	 
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
					 sb.append(" and status=1 and dateBegin<=now() and dateEnd>= now() ");
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
					 sb.append(" and status=1 and dateBegin<=now() and dateEnd>= now() ");
				 }
				 
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
}
