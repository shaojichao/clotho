package com.runmit.clotho.core.mapper;

import com.runmit.clotho.core.domain.CountryCode;
import com.runmit.clotho.core.domain.picture.WeeklyPicture;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author hongbin.cao
 *
 * @date 2015年4月7日
 */
public interface WeeklyPictureMapper {
	@Insert("INSERT INTO WeeklyPicture (`url`,`filesize`,`createby`,`comment`) "
			+ "values (#{url},#{filesize},#{createby},#{comment})")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void saveWeeklyPicture(WeeklyPicture weeklyPicture);
	
	@Update("update WeeklyPicture set url=#{url},filesize=#{filesize},comment=#{comment},updateby=#{updateby},updatetime=now() where id=#{id}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void updateWeeklyPicture(WeeklyPicture weeklyPicture);
	
	@Select("select * from WeeklyPicture ORDER BY id DESC")
	List<WeeklyPicture> getPictureList();
	
	@Select("select * from WeeklyPicture where id=#{id}")
    WeeklyPicture getWeeklyPicture(@Param("id") int id);
	
	@Delete("delete from WeeklyPicture where id=#{id}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void delete(@Param("id") int id);
}
