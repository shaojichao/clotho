package com.runmit.clotho.rest.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.runmit.clotho.rest.domain.WeathersResp;
import net.spy.memcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.runmit.clotho.core.service.WeatherService;
import com.runmit.clotho.rest.common.OkHttpClientSingleton;
import com.runmit.clotho.rest.common.RestConst;
import com.runmit.clotho.rest.domain.CommonResp;
import com.runmit.clotho.rest.domain.WeatherResp;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

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
    @Value("${apistore.baidu.recentweathers.url}")
    private String recentweathersUrl;
    @Value("${apistore.baidu.pinyinweathers.url}")
    private String pinyinweathersUrl;
    @Value("${apistore.baidu.key}")
    private String key;
    @Resource
    private MemcachedClient memcachedClient;
    @Autowired
    private WeatherService weatherService;

//    /**
//     * getWeatherForecast from baidu
//     * @param cityid
//     *
//     * @return resp WeatherForecastData
//     */
    /*@RequestMapping(value = "/getWeatherForecast")
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
                    String pinyin = this.weatherService.getWeatherAreaEName(Integer.valueOf(cityid));
                    if(null == pinyin){
                        pinyin = retData.getString("pinyin");
                    }
                    weatherResp.setPinyin(pinyin);
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
                    WeatherResp weather = (WeatherResp) memcachedClient.get("weather_city_never_exp_"+cityid);
                    if(null != weather){
                        resp.setData(weather);
                        resp.setRtn(RestConst.RTN_OK);
                        resp.setRtmsg("success");
                        LOGGER.info("GET "+url+"?cityid="+cityid + " failed");
                    }else{
                        resp.setRtn(RestConst.RTN_ERROR);
                        resp.setRtmsg("not found the city");
                    }
                }
            }
        }catch(Exception ex){
            LOGGER.error("getWeatherForecast error", ex);
            resp.setRtn(RestConst.RTN_ERROR);
            resp.setRtmsg("failed");
        }
        return resp;
    }*/


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
        try {
//            Response response = client.newCall(request).execute();
//            String result = response.body().string();
//            JSONObject jo = JSONObject.parseObject(result);
//
//            System.out.println(jo);
//            if( null!=jo ){
//                JSONObject retData = jo.getJSONObject("retData");
//                System.out.println(retData);
//                WeathersResp weathersResp = JSONObject.toJavaObject(retData, WeathersResp.class);
//                System.out.println( weathersResp.getCity() );
//            }


            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

            String strNow = sdf.format(now);
            // 每小时进行city缓存
            WeatherResp weatherRespTemp =  (WeatherResp)memcachedClient.get("weather_city_"+cityid+"_"+strNow);
            if ( weatherRespTemp != null){
                resp.setData(weatherRespTemp);
                resp.setRtn(RestConst.RTN_OK);
                resp.setRtmsg("success");
                LOGGER.info("GET "+url+"?cityid="+cityid + " From memcached succeed");
            }else{
//            根据城市代码查天气
            String rtn = getWeatherService(recentweathersUrl+cityid);
            JSONObject rtnJSON = JSONObject.parseObject(rtn);
            if ("0".equals(rtnJSON.getString("errNum"))){
                JSONObject retData = rtnJSON.getJSONObject("retData");
                JSONArray forecast = retData.getJSONArray("forecast");
                JSONObject todayJson = forecast.getJSONObject(0);

                weatherResp.setCity(retData.getString("city"));

                String pinyin = this.weatherService.getWeatherAreaEName(Integer.valueOf(cityid));
                if(null == pinyin){
                    pinyin = retData.getString("pinyin");
                }
                weatherResp.setPinyin(pinyin);

                weatherResp.setCitycode(cityid);
                weatherResp.setDate(sd.format(now).substring(2));
                weatherResp.setTime(now.getHours()+":00");
                weatherResp.setWeather(todayJson.getString("type"));
                // weatherCode :1//使用baidu.weather反查  Weather 表
                String weatherCode = weatherService.getWeatherCode(todayJson.getString("type"));
                weatherResp.setWeatherCode(weatherCode);

                weatherResp.setTemp(todayJson.getString("hightemp").split("\\u2103")[0]);
                weatherResp.setLTemp(todayJson.getString("lowtemp").split("\\u2103")[0]);
                weatherResp.setHTemp(todayJson.getString("hightemp").split("\\u2103")[0]);
                weatherResp.setWD(todayJson.getString("fengxiang"));

                // WDCode :0//使用baidu.WD反查WeatherWindDirection表
                String WDCode = weatherService.getWeatherWindDirectionCode(todayJson.getString("fengxiang"));
                weatherResp.setWDCode(WDCode);

                weatherResp.setWS(todayJson.getString("fengli"));
                // WSCode :0//使用baidu.WS反查WeatherWind表
//                String WSCode = weatherService.getWeatherWindCode(todayJson.getString("fengli"));
                weatherResp.setWSCode("");
                weatherResp.setSunrise("");
                weatherResp.setSunset("");

                resp.setData(weatherResp);
                    memcachedClient.set("weather_city_" + cityid + "_" + strNow, 3600, weatherResp);
                    memcachedClient.set("weather_city_never_exp_"+cityid,0,weatherResp);
                resp.setRtn(RestConst.RTN_OK);
                resp.setRtmsg("success");
                LOGGER.info("recent 7 days cast to today "+url+"?cityid="+cityid + " succeed");

                System.out.println(".//////////"+resp.toString());
                }else {
                    WeatherResp weather = (WeatherResp) memcachedClient.get("weather_city_never_exp_"+cityid);
                    if(null != weather){
                        resp.setData(weather);
                        resp.setRtn(RestConst.RTN_OK);
                        resp.setRtmsg("success");
                        LOGGER.info("GET "+url+"?cityid="+cityid + " failed");
                    }else{
                        resp.setRtn(RestConst.RTN_ERROR);
                        resp.setRtmsg("not found the city");
                    }
                }
            }
        } catch (Exception ex) {
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


//    public static void main(String[] args){
//        System.out.println(111111);
//
////        String url = "http://apis.baidu.com/apistore/weatherservice/recentweathers?cityid=101010100";
////        String url = "http://apis.baidu.com/apistore/weatherservice/cityid?cityid=101010100";
//        String url = "http://apis.baidu.com/apistore/weatherservice/cityname?cityname=BeiJing";
////        String url = "http://apis.baidu.com/apistore/weatherservice/weather?citypinyin=beijing";
////        String url = "http://apis.baidu.com/apistore/weatherservice/citylist";
//
//
//
//        String key = "dbc7dcddec11be599f1a7dada10cb17a";
//
//        OkHttpClient client = OkHttpClientSingleton.getInstance();
//        Request request = new Request.Builder().url(url).header("apikey",key).build();
//        try {
//            Response response = client.newCall(request).execute();
//            String result = response.body().string();
////            System.out.println( "result:"+result );
//            JSONObject jo = JSONObject.parseObject(result);
//
//            System.out.println(jo);
////            System.out.println( jo );
//            if( null!=jo ){
//                JSONObject retData = jo.getJSONObject("retData");
//                System.out.println(retData);
//                WeathersResp weathersResp = JSONObject.toJavaObject(retData, WeathersResp.class);
//                System.out.println( weathersResp.getCity() );
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public static void main(String[] args){
        System.out.println(111111);

//        Date now1 = new Date();
//        System.out.println(now1.getHours());


        String url = "http://apis.baidu.com/apistore/weatherservice/recentweathers?cityid=101010100";
        String key = "dbc7dcddec11be599f1a7dada10cb17a";

        OkHttpClient client = OkHttpClientSingleton.getInstance();
        Request request = new Request.Builder().url(url).header("apikey",key).build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            JSONObject jo = JSONObject.parseObject(result);

            System.out.println(jo);
            if( null!=jo ){
                JSONObject retData = jo.getJSONObject("retData");
                System.out.println(retData);
                WeathersResp weathersResp = JSONObject.toJavaObject(retData, WeathersResp.class);
                System.out.println( weathersResp.getCity() );
            }

            CommonResp resp = new CommonResp();
            WeatherResp weatherResp = new WeatherResp();

            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            String strNow = sdf.format(now);
            // 每小时进行city缓存
//            WeatherResp weatherRespTemp =  (WeatherResp)memcachedClient.get("weather_city_"+101010100+"_"+strNow);
//            if ( weatherRespTemp != null){
//                resp.setData(weatherRespTemp);
//                resp.setRtn(RestConst.RTN_OK);
//                resp.setRtmsg("success");
//                LOGGER.info("GET "+url+"?cityid="+101010100 + " From memcached succeed");
//            }else{
                // 根据城市代码查天气
                String rtn = result;
                JSONObject rtnJSON = JSONObject.parseObject(rtn);
                if ("0".equals(rtnJSON.getString("errNum"))){
                    JSONObject retData = rtnJSON.getJSONObject("retData");
                    JSONArray forecast = retData.getJSONArray("forecast");
                    JSONObject todayJson = forecast.getJSONObject(0);

                    weatherResp.setCity(todayJson.getString("city"));

                    /*String pinyin = this.weatherService.getWeatherAreaEName(Integer.valueOf(101010100));
                    if(null == pinyin){
                        pinyin = retData.getString("pinyin");
                    }
                    weatherResp.setPinyin(pinyin);*/

                    weatherResp.setCitycode(todayJson.getString("citycode"));
                    weatherResp.setDate(sd.format(now));
//                    weatherResp.setTime(todayJson.getString("time"));
                    weatherResp.setWeather(todayJson.getString("type"));
                    weatherResp.setWeather(todayJson.getString("type"));
                    // weatherCode :1//使用baidu.weather反查  Weather 表
                    /*String weatherCode = weatherService.getWeatherCode(retData.getString("weather"));
                    weatherResp.setWeatherCode(weatherCode);
                    weatherResp.setTemp(retData.getString("temp"));
                    weatherResp.setLTemp(retData.getString("l_tmp"));
                    weatherResp.setHTemp(retData.getString("h_tmp"));
                    weatherResp.setWD(retData.getString("WD"));*/

                    /*// WDCode :0//使用baidu.WD反查WeatherWindDirection表
                    String WDCode = weatherService.getWeatherWindDirectionCode(retData.getString("WD"));
                    weatherResp.setWDCode(WDCode);
                    weatherResp.setWS(retData.getString("WS"));*/

                    /*// WSCode :0//使用baidu.WS反查WeatherWind表
                    String WSCode = weatherService.getWeatherWindCode(retData.getString("WS").substring(0, retData.getString("WS").indexOf("(")));
                    weatherResp.setWSCode(WSCode);
                    weatherResp.setSunrise(retData.getString("sunrise"));
                    weatherResp.setSunset(retData.getString("sunset"));*/

                    resp.setData(weatherResp);
//                    memcachedClient.set("weather_city_" + "" + "_" + strNow, 3600, weatherResp);
//                    memcachedClient.set("weather_city_never_exp_"+101010100,0,weatherResp);
                    resp.setRtn(RestConst.RTN_OK);
                    resp.setRtmsg("success");
//                    LOGGER.info("GET "+url+"?cityid="+101010100 + " succeed");

                    System.out.println(".//////////"+resp.toString());
//                }else {
//                    WeatherResp weather = (WeatherResp) memcachedClient.get("weather_city_never_exp_"+101010100);
//                    if(null != weather){
//                        resp.setData(weather);
//                        resp.setRtn(RestConst.RTN_OK);
//                        resp.setRtmsg("success");
//                        LOGGER.info("GET "+url+"?cityid="+101010100 + " failed");
//                    }else{
//                        resp.setRtn(RestConst.RTN_ERROR);
//                        resp.setRtmsg("not found the city");
//                    }
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*

    /**
     *
     * @param cityid
     *
     * @return resp getRecentweathers
     */
    @RequestMapping(value = "/getRecentweathers")
    public CommonResp getRecentweathers(@RequestParam("cityid") String cityid){
        CommonResp resp = new CommonResp();
        try{
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
            String strNow = sdf.format(now);
            // 每小时进行city缓存
            WeathersResp weathersResp =  (WeathersResp)memcachedClient.get("recentweathers_city_"+cityid+"_"+strNow);
            if ( weathersResp != null){
                resp.setData(weathersResp);
                resp.setRtn(RestConst.RTN_OK);
                resp.setRtmsg("success");
                LOGGER.info("GET recentweathers_city_ "+recentweathersUrl+"?cityid="+cityid + " From memcached succeed");
            }else{
                // 根据城市代码查询天气，带历史7天的数据
                String rtn = getWeatherService(recentweathersUrl+cityid);
                JSONObject jo = JSONObject.parseObject(rtn);
                if( null!=jo ){
                    JSONObject retData = jo.getJSONObject("retData");
                    System.out.println(retData);
                    weathersResp = JSONObject.toJavaObject(retData, WeathersResp.class);
                    System.out.println( weathersResp.getCity() );

                    resp.setData(weathersResp);
                    memcachedClient.set("recentweathers_city_" + cityid + "_" + strNow, 3600, weathersResp);
//                    memcachedClient.set("weather_city_never_exp_"+cityid,0,weatherResp);
                    resp.setRtn(RestConst.RTN_OK);
                    resp.setRtmsg("success");
                    LOGGER.info("memcache set success weathersResp"+recentweathersUrl+"?cityid="+cityid + " succeed");
                }

            }
        }catch(Exception ex){
            LOGGER.error("getWeatherForecast error", ex);
            resp.setRtn(RestConst.RTN_ERROR);
            resp.setRtmsg("failed");
        }

        return resp;
    }

}
