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
	@Insert("INSERT INTO WeeklyPicture (`url`,`filesize`,`createby`,`comment`,`type`,`linkout`) "
			+ "values (#{url},#{filesize},#{createby},#{comment},#{type},#{linkout})")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void saveWeeklyPicture(WeeklyPicture weeklyPicture);
	
	@Update("update WeeklyPicture set url=#{url},type=#{type},linkout=#{linkout},filesize=#{filesize},comment=#{comment},updateby=#{updateby},updatetime=now() where id=#{id}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void updateWeeklyPicture(WeeklyPicture weeklyPicture);

    @Select("<script>select * from WeeklyPicture " +
            "WHERE 1=1 " +
            "<if test=\"type != null\"> AND type = #{type} </if>" +
            "ORDER BY id DESC LIMIT #{start},#{limit} " +
            "</script>" )
	List<WeeklyPicture> getPictureList(@Param("start") int start, @Param("limit") int limit,
                                       @Param("type") String type);

    @Select("SELECT *," +
            "(SELECT url FROM `WeeklyPicture` WHERE TYPE = 2 ORDER BY id DESC LIMIT 1) AS bigImgUrl " +
            "FROM `WeeklyPicture`  WHERE TYPE=1 ORDER BY id DESC LIMIT 1")
    WeeklyPicture getPictureListRest();
	
	@Select("select * from WeeklyPicture where id=#{id}")
    WeeklyPicture getWeeklyPicture(@Param("id") int id);
	
	@Delete("delete from WeeklyPicture where id=#{id}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void delete(@Param("id") int id);
}
