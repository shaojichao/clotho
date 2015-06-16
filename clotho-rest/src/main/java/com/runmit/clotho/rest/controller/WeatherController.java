package com.runmit.clotho.rest.controller;

import com.alibaba.fastjson.JSONObject;
import com.runmit.clotho.core.domain.picture.WeeklyPicture;
import com.runmit.clotho.core.service.WeatherService;
import com.runmit.clotho.core.service.WeeklyPictureService;
import com.runmit.clotho.rest.common.OkHttpClientSingleton;
import com.runmit.clotho.rest.common.RestConst;
import com.runmit.clotho.rest.domain.CommonResp;
import com.runmit.clotho.rest.domain.RespWeeklyPicture;
import com.runmit.clotho.rest.domain.WeatherResp;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author hongbin.cao
 *
 * @date 2015年6月10日
 */
@RestController
@RequestMapping(value = "/weather")
public class WeatherController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);

    @Value("${apistore.baidu.url}")
    private String url;
    @Value("${apistore.baidu.key}")
    private String key;
    @Resource
    private MemcachedClient memcachedClient;
    @Autowired
    private WeatherService weatherService;

    /**
     * getWeatherForecast from baidu
     * @param cityid
     *
     * @return resp WeatherForecastData
     */
    @RequestMapping(value = "/getWeatherForecast")
    public CommonResp getWeatherForecast(@RequestParam("cityid") String cityid){
    	CommonResp resp = new CommonResp();
        WeatherResp weatherResp = new WeatherResp();
    	try{
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
            String strNow = sdf.format(now);
            // 每小时进行city缓存
            WeatherResp weatherRespTemp =  (WeatherResp)memcachedClient.get("weather_city_"+cityid+"_"+strNow);
            if ( weatherRespTemp != null){
                resp.setData(weatherRespTemp);
                resp.setRtn(RestConst.RTN_OK);
                resp.setRtmsg("success");
                LOGGER.info("GET "+url+"?cityid="+cityid + " From memcached succeed");
            }else{
                // 根据城市代码查天气
                String rtn = getWeatherService(url+cityid);
                JSONObject rtnJSON = JSONObject.parseObject(rtn);
                if ("0".equals(rtnJSON.getString("errNum"))){
                    JSONObject retData = rtnJSON.getJSONObject("retData");
                    weatherResp.setCity(retData.getString("city"));
                    weatherResp.setPinyin(retData.getString("pinyin"));
                    weatherResp.setCitycode(retData.getString("citycode"));
                    weatherResp.setDate(retData.getString("date"));
                    weatherResp.setTime(retData.getString("time"));
                    weatherResp.setWeather(retData.getString("weather"));
                    // weatherCode :1//使用baidu.weather反查  Weather 表
                    String weatherCode = weatherService.getWeatherCode(retData.getString("weather"));
                    weatherResp.setWeatherCode(weatherCode);
                    weatherResp.setTemp(retData.getString("temp"));
                    weatherResp.setLTemp(retData.getString("l_tmp"));
                    weatherResp.setHTemp(retData.getString("h_tmp"));
                    weatherResp.setWD(retData.getString("WD"));
                    // WDCode :0//使用baidu.WD反查WeatherWindDirection表
                    String WDCode = weatherService.getWeatherWindDirectionCode(retData.getString("WD"));
                    weatherResp.setWDCode(WDCode);
                    weatherResp.setWS(retData.getString("WS"));
                    // WSCode :0//使用baidu.WS反查WeatherWind表
                    String WSCode = weatherService.getWeatherWindCode(retData.getString("WS").substring(0, retData.getString("WS").indexOf("(")));
                    weatherResp.setWSCode(WSCode);
                    weatherResp.setSunrise(retData.getString("sunrise"));
                    weatherResp.setSunset(retData.getString("sunset"));

                    resp.setData(weatherResp);
                    memcachedClient.set("weather_city_" + cityid + "_" + strNow, 3600, weatherResp);
                    memcachedClient.set("weather_city_never_exp_"+cityid,0,weatherResp);
                    resp.setRtn(RestConst.RTN_OK);
                    resp.setRtmsg("success");
                    LOGGER.info("GET "+url+"?cityid="+cityid + " succeed");
                }else {
                    resp.setData(memcachedClient.get("weather_city_never_exp_"+cityid));
                    resp.setRtn(RestConst.RTN_OK);
                    resp.setRtmsg("success");
                    LOGGER.info("GET "+url+"?cityid="+cityid + " failed");
                }
            }
    	}catch(Exception ex){
    		LOGGER.error("getWeatherForecast error", ex);
    		resp.setRtn(RestConst.RTN_ERROR);
            resp.setRtmsg("failed");
    	}
    	return resp;
    }

    private String getWeatherService(String url) throws IOException {
        // Get OkHttpClientSingleton
        OkHttpClient client = OkHttpClientSingleton.getInstance();
        Request request = new Request.Builder().url(url).header("apikey",key).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
