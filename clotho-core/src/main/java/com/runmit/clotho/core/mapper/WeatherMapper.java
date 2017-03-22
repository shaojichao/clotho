package com.runmit.clotho.core.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author hongbin.cao
 *
 * @date 2015年6月15日
 */
public interface WeatherMapper {

	@Select("SELECT w.code FROM Weather w WHERE w.cname=#{cname}")
    String getWeatherCode(@Param("cname") String cname);

    @Select("SELECT w.code FROM WeatherWind w WHERE w.cname Like #{cname}")
    String getWeatherWindCode(@Param("cname") String cname);

    @Select("SELECT w.code FROM WeatherWindDirection w WHERE w.cname=#{cname}")
    String getWeatherWindDirectionCode(@Param("cname") String cname);
    
    @Select("SELECT ename FROM WeatherAreaV WHERE areaid=#{areaid}")
    String getWeatherAreaEName(@Param("areaid") int areaid);

    @Select("SELECT ename FROM WeatherAreaF WHERE areaid=#{areaid}")
    String getWeatherPinyin(@Param("areaid") int areaid);


}
