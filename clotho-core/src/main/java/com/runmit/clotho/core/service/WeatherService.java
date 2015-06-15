package com.runmit.clotho.core.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.runmit.clotho.core.domain.picture.WeeklyPicture;
import com.runmit.clotho.core.mapper.WeatherMapper;
import com.runmit.clotho.core.mapper.WeeklyPictureMapper;
import com.runmit.clotho.log.domain.OpLog.OpType;
import com.runmit.clotho.log.service.OpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hongbin.cao
 *
 * @date 2015年6月15日
 */
@Service
@Transactional
public class WeatherService {

	@Autowired
    private WeatherMapper weatherMapper;
    @Reference(version="1.0.0")
	private OpLogService opLogService;

    @Transactional(readOnly = true)
    public String getWeatherCode(String cname){
        return this.weatherMapper.getWeatherCode(cname);
    }

    @Transactional(readOnly = true)
    public String getWeatherWindCode(String cname){
        return this.weatherMapper.getWeatherWindCode(cname);
    }

    @Transactional(readOnly = true)
    public String getWeatherWindDirectionCode(String cname){
        return this.weatherMapper.getWeatherWindDirectionCode(cname);
    }



}
