package com.runmit.clotho.management.controller.browser;

import com.runmit.clotho.core.domain.browser.Advertisement;
import com.runmit.clotho.core.domain.browser.PhoneModel;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.browser.AdvertisementService;
import com.runmit.clotho.core.service.browser.PhoneModelService;
import com.runmit.clotho.core.util.DateUtils;
import com.runmit.clotho.management.security.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author sjc
 * @date 2017-05-31-16:39
 */
@RestController
@RequestMapping(value = "/ad")
public class AdvertisementController{
    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertisementController.class);

    @Value("${file.download.url}")
    private String downloadUrl;

    @Autowired
    private AdvertisementService adService;
    @Autowired
    private PhoneModelService phoneModelService;

    /**
     * 修改开屏广告状态
     * @return
     */
    @RequestMapping(value = "/updateAdStatus.do", method = RequestMethod.POST)
    public ExtStatusEntity updateAdStatus(@RequestParam("id") Integer id,
                                          @RequestParam("status")Integer status,
                                          HttpServletRequest request){
        ExtStatusEntity entity = new ExtStatusEntity();
        Advertisement ad = new Advertisement();
        try {
            ad.setUpdateBy(SessionUtil.getLoginAdminName(request));
            ad.setId(id);
            ad.setStatus(status);
            adService.updateAdInfo(ad);
            entity.setSuccess(true);
            if(status == 1){
                entity.setMsg("上架成功!");
            }else if(status == 2){
                entity.setMsg("下架成功!");
            }
        } catch (Exception ex) {
            LOGGER.error("updateAdStatus error", ex);
            entity.setSuccess(false);
            entity.setMsg("操作失败，请稍候再试!");
        }
        return entity;
    }

    /**
     * 查出所有开屏广告信息
     * @param start
     * @param limit
     * @return
     */
    @RequestMapping(value = "/adList.do")
    public ExtEntity<Advertisement> getadList(
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
            @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit){
        ExtEntity<Advertisement> datas = new ExtEntity<Advertisement>();
        List<Advertisement> list = adService.getList(downloadUrl, start, limit);
        datas.setRows(list);
        datas.setResult(adService.getCount());
        LOGGER.info("----------- getadList");
        return datas;
    }

    /**
     * 保存广告
     * @param advertisement
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveAd.do", method = RequestMethod.POST)
    public ExtStatusEntity saveAdvertisement(Advertisement advertisement, HttpServletRequest request) {
        ExtStatusEntity entity = new ExtStatusEntity();
        try{
            advertisement.setCreateBy(SessionUtil.getLoginAdminName(request));
            advertisement.setUpdateBy(SessionUtil.getLoginAdminName(request));
            advertisement.setStatus(0);
            adService.addAdInfo(advertisement);
            entity.setMsg("succeed");
            entity.setSuccess(true);
        }catch(Exception ex){
            LOGGER.error("saveAd error",ex);
            entity.setMsg("保存失败");
            entity.setSuccess(false);
        }
        return entity;
    }

    /**
     * 生成版本号
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/generateVersionCode.do")
    public ExtStatusEntity generateVersionCode(@RequestParam("id") Integer id,HttpServletRequest request){
        ExtStatusEntity entity = new ExtStatusEntity();
        try{
            Advertisement ad = new Advertisement();
            String versionCode= DateUtils.generateVersionNum();
            ad.setId(id);
            ad.setVersion(versionCode);
            ad.setUpdateBy(SessionUtil.getLoginAdminName(request));
            adService.updateAdInfo(ad);
            entity.setMsg(versionCode);
            entity.setSuccess(true);
        }catch(Exception ex){
            LOGGER.error("--------- generateVersionCode error", ex);
            entity.setMsg("生成版本号失败!稍后重试!");
            entity.setSuccess(false);
        }
        return entity;
    }

    /**
     * 查出所有机型信息
     * @return
     */
    @RequestMapping(value = "/phoneList.do", method = RequestMethod.GET)
    public ExtEntity<PhoneModel> getPhoneModelList(){
        ExtEntity<PhoneModel> datas = new ExtEntity<PhoneModel>();
        List<PhoneModel> phoneModelList = phoneModelService.getPhoneModelList();
        datas.setRows(phoneModelList);
        datas.setResult(phoneModelService.getCount(null));
        LOGGER.info("----------- getPhoneModelList");
        return datas;
    }
}
