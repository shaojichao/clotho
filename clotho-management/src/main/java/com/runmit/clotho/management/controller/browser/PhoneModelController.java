package com.runmit.clotho.management.controller.browser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.runmit.clotho.core.domain.Page;
import com.runmit.clotho.core.domain.browser.PhoneModel;
import com.runmit.clotho.core.domain.browser.PhoneModelResponse;
import com.runmit.clotho.core.service.browser.PhoneModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sjc
 * @date 2017-05-31-16:39
 */
@Controller
@Component
@RequestMapping(value = "/phone")
public class PhoneModelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneModelController.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Autowired
    private PhoneModelService phoneModelService;

    /**
     * 通过条件查询机型信息
     * @param model 机型名字
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping(value = "/phoneList.do", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<PhoneModelResponse>> getPhoneModelList(
            @RequestParam(required = false) String model,
            @RequestParam(required = false, defaultValue = "20") int limit,
            @RequestParam(required = false, defaultValue = "0") int page){
        Page<PhoneModel> phoneModelList = phoneModelService.getPhoneModelList(model, limit, page);
        return new ResponseEntity<>(
                coverPhoneModelToResponseEntity(phoneModelList), HttpStatus.OK);
    }

    private Page<PhoneModelResponse> coverPhoneModelToResponseEntity(Page<PhoneModel> phoneModelPage){
        List<PhoneModelResponse> datas = new ArrayList<>();
        for (PhoneModel modelDoc : phoneModelPage.getDatas()) {
            PhoneModelResponse phoneModelResponse = new PhoneModelResponse();
            BeanUtils.copyProperties(modelDoc, phoneModelResponse);
            datas.add(phoneModelResponse);
        }
        return new Page<>(
                datas,
                phoneModelPage.getTotal()
        );
    }
}
