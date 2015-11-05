package com.runmit.clotho.core.mapper;

import com.runmit.clotho.core.domain.picture.WeeklyPicture;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author hongbin.cao
 *
 * @date 2015年4月7日
 */
public interface WeeklyPictureMapper {
	@Insert("INSERT INTO WeeklyPicture (`url`,`filesize`,`createby`,`comment`,`type`,`linkout`,`md5`,`distributestatus`,`language`) "
			+ "values (#{url},#{filesize},#{createby},#{comment},#{type},#{linkout},#{md5},#{distributestatus},#{language})")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void saveWeeklyPicture(WeeklyPicture weeklyPicture);
	
	@Update("update WeeklyPicture set distributestatus=#{distributestatus},url=#{url},type=#{type},language=#{language},linkout=#{linkout},filesize=#{filesize},md5=#{md5},comment=#{comment},updateby=#{updateby},updatetime=now() where id=#{id}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void updateWeeklyPicture(WeeklyPicture weeklyPicture);

    @Select("<script>select * from WeeklyPicture " +
            "WHERE 1 = 1 " +
            "<if test=\"type != null\"> AND type = #{type} </if>" +
            "<if test=\"type == null\"> AND type != 3 </if>" +
            "<if test=\"language != null\"> AND language = #{language} </if>" +
            "ORDER BY id DESC LIMIT #{start},#{limit} " +
            "</script>" )
	List<WeeklyPicture> getPictureList(@Param("start") int start, @Param("limit") int limit,
                                       @Param("type") String type, @Param("language") String language);
    
    @Select("<script>select count(id) from WeeklyPicture " +
            "WHERE 1 = 1 " +
            "<if test=\"type != null\"> AND type = #{type} </if>" +
            "<if test=\"language != null\"> AND language = #{language} </if>" +
            "</script>" )
	long getCount(@Param("type") String type, @Param("language") String language);

    @Select("SELECT *," +
            "(SELECT url FROM `WeeklyPicture` WHERE TYPE = 2 AND language = #{lang} ORDER BY id DESC LIMIT 1) AS bigImgUrl " +
            "FROM `WeeklyPicture`  WHERE TYPE=1 AND language = #{lang} ORDER BY id DESC LIMIT 1")
    WeeklyPicture getPictureListRest(@Param("lang") String lang);
	
	@Select("select * from WeeklyPicture where id=#{id}")
    WeeklyPicture getWeeklyPicture(@Param("id") int id);
	
	@Select("select * from WeeklyPicture where type=#{type} and language = #{language} ORDER BY id DESC")
	List<WeeklyPicture> getWeeklyPictureByType(@Param("type") int type, @Param("language") String language);
	
	@Delete("delete from WeeklyPicture where id=#{id}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void delete(@Param("id") int id);

}
