package com.runmit.clotho.rest.controller;

import com.alibaba.fastjson.JSONObject;
import com.runmit.clotho.core.domain.picture.WeeklyPicture;
import com.runmit.clotho.core.service.WeatherService;
import com.runmit.clotho.core.service.WeeklyPictureService;
import com.runmit.clotho.rest.common.OkHttpClientSingleton;
import com.runmit.clotho.rest.common.RestConst;
import com.runmit.clotho.rest.domain.CommonResp;
import com.runmit.clotho.rest.domain.RespWeeklyPicture;
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
    private RestTemplate restTemplate;
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
    	try{
            // 根据城市代码查天气
            String rtn = getWeatherService(url+cityid);
            JSONObject rtnJSON = JSONObject.parseObject(rtn);
            if ("0".equals(rtnJSON.getString("errNum"))){
                JSONObject retData = rtnJSON.getJSONObject("retData");
                memcachedClient.set("city", 0, retData.getString("city"));
                memcachedClient.set("pinyin", 0, retData.getString("pinyin"));
                memcachedClient.set("citycode", 0, retData.getString("citycode"));
                memcachedClient.set("date", 0, retData.getString("date"));
                memcachedClient.set("time", 0, retData.getString("time"));
                memcachedClient.set("postCode", 0, retData.getString("postCode"));
                memcachedClient.set("longitude", 0, retData.getString("longitude"));
                memcachedClient.set("latitude", 0, retData.getString("latitude"));
                memcachedClient.set("altitude", 0, retData.getString("altitude"));
                memcachedClient.set("weather", 0, retData.getString("weather"));
                // weatherCode :1//使用baidu.weather反查  Weather 表
                String weatherCode = weatherService.getWeatherCode(retData.getString("weather"));
                memcachedClient.set("weatherCode", 0, weatherCode);
                retData.put("weatherCode",weatherCode);
                memcachedClient.set("temp", 0, retData.getString("temp"));
                memcachedClient.set("l_tmp", 0, retData.getString("l_tmp"));
                memcachedClient.set("h_tmp", 0, retData.getString("h_tmp"));
                memcachedClient.set("WD", 0, retData.getString("WD"));
                // WDCode :0//使用baidu.WD反查WeatherWindDirection表
                String WDCode = weatherService.getWeatherWindDirectionCode(retData.getString("WD"));
                retData.put("WDCode",WDCode);
                memcachedClient.set("WDCode", 0, WDCode);
                memcachedClient.set("WS", 0, retData.getString("WS"));
                // WSCode :0//使用baidu.WS反查WeatherWind表
                String WSCode = weatherService.getWeatherWindCode(retData.getString("WS").substring(0, retData.getString("WS").indexOf("(")));
                retData.put("WSCode",WSCode);
                memcachedClient.set("WSCode", 0, WSCode);
                memcachedClient.set("sunrise", 0, retData.getString("sunrise"));
                memcachedClient.set("sunset", 0, retData.getString("sunset"));

                resp.setData(retData);
                resp.setRtn(RestConst.RTN_OK);
                resp.setRtmsg("success");
                LOGGER.info("GET "+url+"?cityid="+cityid + "succeed");
            }else {
                resp.setData(null);
                resp.setRtn(RestConst.RTN_ERROR);
                resp.setRtmsg("failed");
                LOGGER.info("GET "+url+"?cityid="+cityid + "failed");
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
