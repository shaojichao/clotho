package com.runmit.clotho.rest.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.runmit.clotho.core.domain.VersionConstant;
import com.runmit.clotho.core.domain.browser.*;
import com.runmit.clotho.core.service.browser.*;
import com.runmit.clotho.rest.common.RestConst;
import com.runmit.clotho.rest.domain.*;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * 浏览器管理后台Controller
 * @author lgz
 * @version 1.0
 * @date 2017-06-07
 */
@RestController
public class BrowserController{
	private static final Logger LOGGER = LoggerFactory.getLogger(BrowserController.class);

    @Autowired
    private PhoneModelService modelService;

    @Autowired
    private AdvertisementService adService;

    @Autowired
    private InfoVersionService versionService;

    @Autowired
    private SearchEngineService engineService;

    @Autowired
    private UserEngineService userEngineService;

    @Autowired
    private NavigationService navigationService;

    @Value("${user.center.check.api.address}")
    private String checkUrl;

	/**
     *1、根据机型ID获取开屏广告信息列表
     * @param id 机型ID
     * @param version 版本号
     * @return
     */
    @RequestMapping(value = "/ad/getAdList/{id}",method = RequestMethod.GET)
    public CommonResp getAdList(@PathVariable int id, @RequestParam(required = false) String version){
        CommonResp resp = new CommonResp();
        try{
            PhoneModel model = modelService.getModelById(id);
            if(model == null){
                resp.setRtn(RestConst.RTN_FAILED);
                resp.setRtmsg("ID为【"+id+"】的机型不存在!");
                return resp;
            }
            if(StringUtils.isEmpty(version)){
                resp.setRtn(RestConst.RTN_FAILED);
                resp.setRtmsg("版本号不能为空!");
                return resp;
            }
            //检查输入版本号是否合法(纯数字)
            boolean isNum = version.matches("[0-9]+");
            if(!isNum){
                resp.setRtn(RestConst.RTN_FAILED);
                resp.setRtmsg("输入的版本号包含非法数字!");
                return resp;
            }

            AdvertisementResp adResp=new AdvertisementResp();
            //查找当前机型开屏广告的版本号
            List<Advertisement> adList = adService.getAdListByModeId(id,null);
            if(adList.size() > 0){
                //当前版本
                BigDecimal inputVersion=new BigDecimal(version);
                //最新版本
                BigDecimal latestVersion=new BigDecimal(adList.get(0).getVersion());

                if(latestVersion.compareTo(inputVersion) > 0){
                    adResp.setId(id);
                    adResp.setModel(model.getModel());
                    adResp.setAdList(adList);
                    resp.setData(adResp);
                }
            }
            resp.setRtn(RestConst.RTN_OK);
            resp.setRtmsg("success");
        }catch(Exception e){
            LOGGER.error("BrowserController.getAdList error", e);
            resp.setRtn(RestConst.RTN_FAILED);
            resp.setRtmsg("exception");
        }
        return resp;
    }

    /**
     * 2、根据版本号获取高于当前版本号的搜索引擎信息列表
     * @param versionNo 版本号
     * @param start 开始页数
     * @param limit 每页条数
     * @return
     */
    @RequestMapping(value = "/engine/getEngineList/{versionNo}",method = RequestMethod.GET)
    public CommonResp getEngineList(@PathVariable String versionNo,
                                    @RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
                                    @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit){
        CommonResp resp = new CommonResp();
        try{
            //检查输入版本号是否合法(纯数字)
            boolean isNum = versionNo.matches("[0-9]+");
            if(!isNum){
                resp.setRtn(RestConst.RTN_FAILED);
                resp.setRtmsg("输入的版本号包含非法数字!");
                return resp;
            }

            //获取搜索引擎版本key对应的版本号
            InfoVersion version = versionService.getVersionNum(VersionConstant.ENGINE_KEY);
            if(version == null){
                resp.setRtn(RestConst.RTN_FAILED);
                resp.setRtmsg("暂无版本信息!");
                return resp;
            }
            //最新版本号
            BigDecimal latestVersion=new BigDecimal(version.getVersionNo());
            //输入的版本号
            BigDecimal inputNo=new BigDecimal(versionNo);
            //检查输入版本号是否低于当前版本号
            SearchEngineResp engineResp=new SearchEngineResp();
            if(latestVersion.compareTo(inputNo) > 0){
                List<SearchEngine> engineList = engineService.getList(start, limit);
                long total = engineService.getCount();
                engineResp.setVersionNo(version.getVersionNo());
                engineResp.setTotal(total);
                engineResp.setEngineList(engineList);
            }
            resp.setData(engineResp);
            resp.setRtn(RestConst.RTN_OK);
            resp.setRtmsg("success");
        }catch(Exception e){
            LOGGER.error("BrowserController.getEngineList error", e);
            resp.setRtn(RestConst.RTN_FAILED);
            resp.setRtmsg("exception");
        }
        return resp;
    }

    /**
     *3、用户个性化配置默认搜索引擎接口
     * @param token
     * @param clientId
     * @param engineId 搜索引擎ID
     * @return
     */
    @RequestMapping(value = "/engine/setDefaultEngine", method = RequestMethod.GET)
    public CommonResp setDefaultEngine(@RequestParam(value="token", required=true) String token,
                                       @RequestParam(value="clientId",required=true) String clientId,
                                       @RequestParam(value="engineId", required=true)  Integer engineId){
        CommonResp resp = new CommonResp();
        try{
            if(engineId == null){
                resp.setRtn(RestConst.RTN_FAILED);
                resp.setRtmsg("引擎ID不能为空");
                return resp;
            }
            //用户鉴权
            UserCheckedDTO userCheckedDTO = userCheck(token, clientId);
            if(userCheckedDTO == null || userCheckedDTO.getRtn() != 0){
                resp.setRtn(RestConst.RTN_FAILED);
                resp.setRtmsg("用户鉴权失败!");
                return resp;
            }
            UserInfo userInfo = userCheckedDTO.getUserInfo();

            //查找当前用户是否已设置过默认搜索引擎信息
            List<UserEngine> list = userEngineService.getUserDefaultEngine(userInfo.getUserid());
            if(list.size() > 0){
                resp.setRtn(RestConst.RTN_FAILED);
                resp.setRtmsg(RestConst.RTN_FAILED_REPEAT);
                return resp;
            }

            //添加用户默认搜索引擎
            UserEngine po=new UserEngine();
            po.setUserId(userInfo.getUserid());
            po.setEngineId(engineId);
            po.setCreateBy(userInfo.getName());
            po.setUpdateBy(userInfo.getName());
            int rel = userEngineService.saveUserEngineInfo(po);
            if(rel > 0){
                resp.setRtn(RestConst.RTN_OK);
                resp.setRtmsg("success");
            }else{
                resp.setRtn(RestConst.RTN_FAILED);
                resp.setRtmsg("设置失败!");
            }
        }catch(Exception e){
            LOGGER.error("BrowserController.setDefaultEngine error", e);
            resp.setRtn(RestConst.RTN_FAILED);
            resp.setRtmsg("exception");
        }
        return resp;
    }

    /**
     *4、获取个性化默认搜索引擎接口
     * @param userId 用户ID
     * @return
     */
    @RequestMapping(value = "/engine/getDefaultEngine/{userId}", method = RequestMethod.GET)
    public CommonResp getDefaultEngine(@PathVariable Integer userId){
        CommonResp resp = new CommonResp();
        try{
            if(userId == null){
                resp.setRtn(RestConst.RTN_FAILED);
                resp.setRtmsg("用户ID不能为空");
                return resp;
            }
            //查找当前用户已设置的默认搜索引擎信息
            List<UserEngine> list = userEngineService.getUserDefaultEngine(userId);
            if(list.size() > 0){
                resp.setData(list.get(0));
            }
            resp.setRtn(RestConst.RTN_OK);
            resp.setRtmsg("success");
        }catch(Exception e){
            LOGGER.error("BrowserController.getDefaultEngine error", e);
            resp.setRtn(RestConst.RTN_FAILED);
            resp.setRtmsg("exception");
        }
        return resp;
    }

    /**
     * 5、根据版本号获取高于当前版本号的导航栏信息列表
     * @param versionNo 版本号
     * @param start 开始页数
     * @param limit 每页条数
     * @return
     */
    @RequestMapping(value = "/engine/getNavList/{versionNo}",method = RequestMethod.GET)
    public CommonResp getNavList(@PathVariable String versionNo,
                                    @RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
                                    @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit){
        CommonResp resp = new CommonResp();
        try{
            //检查输入版本号是否合法(纯数字)
            boolean isNum = versionNo.matches("[0-9]+");
            if(!isNum){
                resp.setRtn(RestConst.RTN_FAILED);
                resp.setRtmsg("输入的版本号包含非法数字!");
                return resp;
            }

            //获取导航栏版本key对应的版本号
            InfoVersion version = versionService.getVersionNum(VersionConstant.NAVIGATION_KEY);
            if(version == null){
                resp.setRtn(RestConst.RTN_FAILED);
                resp.setRtmsg("暂无版本信息!");
                return resp;
            }
            //最新版本号
            BigDecimal latestVersion=new BigDecimal(version.getVersionNo());
            //输入的版本号
            BigDecimal inputNo=new BigDecimal(versionNo);
            //检查输入版本号是否低于当前版本号
            NavigationResp navResp=new NavigationResp();
            if(latestVersion.compareTo(inputNo) > 0){
                List<Navigation> list = navigationService.getList(null, start, limit);
                long total = navigationService.getCount(null);
                navResp.setVersionNo(version.getVersionNo());
                navResp.setTotal(total);
                navResp.setNavList(list);
            }
            resp.setData(navResp);
            resp.setRtn(RestConst.RTN_OK);
            resp.setRtmsg("success");
        }catch(Exception e){
            LOGGER.error("BrowserController.getNavList error", e);
            resp.setRtn(RestConst.RTN_FAILED);
            resp.setRtmsg("exception");
        }
        return resp;
    }

    /**
     * 用户鉴权
     * @param token
     * @param clientId
     * @return
     * @throws IOException
     */
    private UserCheckedDTO userCheck(String token, String clientId) throws IOException{
        String requestUrl = checkUrl + token;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(requestUrl);
        httpGet.setHeader("superProjectId", clientId);
        httpGet.setHeader("language", "zh_CN");
        httpGet.setHeader("clientId", clientId);

        HttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            ObjectMapper mapper = new ObjectMapper();
            org.apache.http.HttpEntity entity = response.getEntity();
            UserCheckedDTO userChecked = mapper.readValue(entity.getContent(), UserCheckedDTO.class);
            return userChecked;
        }
        return null;
    }

}
