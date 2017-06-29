package com.runmit.clotho.management.controller.browser;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.runmit.clotho.core.domain.browser.PhoneModel;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.browser.PhoneModelService;
import com.runmit.clotho.management.security.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author sjc
 * @date 2017-05-31-16:39
 */
@RestController
@RequestMapping(value = "/phone")
public class PhoneModelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneModelController.class);
    @Autowired
    private PhoneModelService phoneModelService;

    /**
     * 删除机型信息
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/deletePhone.do", method = RequestMethod.POST)
    public ExtStatusEntity deletePhone(@RequestParam("id") Integer id,HttpServletRequest request){
        ExtStatusEntity entity = new ExtStatusEntity();
        try {
            int count=phoneModelService.deletePhone(id);
            if(count > 0){
                entity.setSuccess(true);
                entity.setMsg("删除成功!");
            }else{
                entity.setSuccess(false);
                entity.setMsg("删除失败!");
            }
        } catch (Exception ex) {
            LOGGER.error("deletePhone error", ex);
            entity.setSuccess(false);
            entity.setMsg("删除失败!");
        }
        return entity;
    }

    /**
     * 新增或修改当前机型信息
     * @param phoneModel 机型对象
     * @param request
     * @return
     */
    @RequestMapping(value = "/savePhoneModel.do", method = RequestMethod.POST)
    public ExtStatusEntity savePhoneModel(PhoneModel phoneModel, HttpServletRequest request) {
        ExtStatusEntity entity = new ExtStatusEntity();
        try{
            //检查分辨率是否已存在
            PhoneModel po = phoneModelService.getModelByResolution(phoneModel.getWidth(), phoneModel.getHeight());
            if(po != null){
                LOGGER.info("---------- 分辨率为【"+phoneModel.getWidth()+"*"+phoneModel.getHeight()+"】的机型信息已存在!");
                entity.setMsg("【"+phoneModel.getWidth()+"*"+phoneModel.getHeight()+"】的分辨率信息已存在");
                entity.setSuccess(false);
                return entity;
            }
            if(phoneModel.getId() == null){
                phoneModel.setCreateBy(SessionUtil.getLoginAdminName(request));
                phoneModel.setUpdateBy(SessionUtil.getLoginAdminName(request));
            }else{
                phoneModel.setUpdateBy(SessionUtil.getLoginAdminName(request));
            }
            phoneModelService.savePhoneModel(phoneModel);
            entity.setMsg("succeed");
            entity.setSuccess(true);
        }catch(Exception ex){
            LOGGER.error("savePhoneModel error",ex);
            entity.setMsg("保存失败");
            entity.setSuccess(false);
        }
        LOGGER.info("savePhoneModel");
        return entity;
    }

    /**
     * 通过机型名字分页查询机型信息
     * @param model 机型名字
     * @param start
     * @param limit
     * @return
     */
    @RequestMapping(value = "/phoneList.do", method = RequestMethod.GET)
    public ExtEntity<PhoneModel> getPhoneModelList(
            @RequestParam(required = false) String model,
            @RequestParam(required = false, defaultValue = "20") int start,
            @RequestParam(required = false, defaultValue = "0") int limit) throws UnsupportedEncodingException {
        ExtEntity<PhoneModel> datas = new ExtEntity<PhoneModel>();
        if(StringUtils.isNotEmpty(model)){
            model = new String(model.getBytes("ISO-8859-1"),"UTF-8");
        }
        List<PhoneModel> phoneModelList = phoneModelService.getPhoneModelPage(model, start, limit);
        datas.setRows(phoneModelList);
        datas.setResult(phoneModelService.getCount(model));
        LOGGER.info("----------- getPhoneModelList");
        return datas;
    }

}
