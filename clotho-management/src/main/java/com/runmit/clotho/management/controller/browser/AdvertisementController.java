package com.runmit.clotho.management.controller.browser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.runmit.clotho.core.domain.browser.Advertisement;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.browser.AdvertisementService;
import com.runmit.clotho.management.security.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author sjc
 * @date 2017-05-31-16:39
 */
@Controller
@Component
@RequestMapping(value = "/ad")
public class AdvertisementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertisementController.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Autowired
    private AdvertisementService adService;

    /**
     * 修改开屏广告状态
     * @return
     */
    @RequestMapping(value = "/updateAdStatus.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtStatusEntity updateAdStatus(@RequestParam("id") Integer id,
                                          @RequestParam("status")Integer status,
                                          HttpServletRequest request){
        ExtStatusEntity entity = new ExtStatusEntity();
        Advertisement ad = new Advertisement();
        try {
            ad.setUpdateBy(SessionUtil.getLoginAdminName(request));
            ad.setId(id);
            if(status == 0){
                ad.setStatus(1);
            }else if(status == 1){
                ad.setStatus(2);
            }
            adService.updateAdInfo(ad);
            entity.setSuccess(true);
            if(status == 0){
                entity.setMsg("上架成功!");
            }else{
                entity.setMsg("下架成功!");
            }
        } catch (Exception ex) {
            LOGGER.error("updateAdStatus error", ex);
            entity.setSuccess(false);
            entity.setMsg("操作失败，请稍候再试!");
        }
        return entity;
    }
//
//    /**
//     * 新增或修改当前机型信息
//     * @param phoneModel 机型对象
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/savePhoneModel.do", method = RequestMethod.POST)
//    @ResponseBody
//    public ExtStatusEntity savePhoneModel(PhoneModel phoneModel, HttpServletRequest request) {
//        ExtStatusEntity entity = new ExtStatusEntity();
//
//        if(phoneModel.getId()==null||phoneModel.getId()==0){
//            phoneModel.setCreateBy(SessionUtil.getLoginAdminName(request));
//            phoneModel.setUpdateBy(SessionUtil.getLoginAdminName(request));
//        }else{
//            phoneModel.setUpdateBy(SessionUtil.getLoginAdminName(request));
//        }
//        try{
//            phoneModelService.savePhoneModel(phoneModel);
//            entity.setMsg("succeed");
//            entity.setSuccess(true);
//        }catch(Exception ex){
//            LOGGER.error("savePhoneModel error",ex);
//            entity.setMsg("保存失败");
//            entity.setSuccess(false);
//        }
//        LOGGER.info("savePhoneModel");
//        return entity;
//    }

    /**
     * 查出所有开屏广告信息
     * @param start
     * @param limit
     * @return
     */
    @RequestMapping(value = "/adList.do")
    @ResponseBody
    public ExtEntity<Advertisement> getEngineList(
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
            @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit){
        ExtEntity<Advertisement> datas = new ExtEntity<Advertisement>();
        List<Advertisement> list = adService.getList(start, limit);
        datas.setRows(list);
        datas.setResult(adService.getCount());
        LOGGER.info("----------- getEngineList");
        return datas;
    }
}
