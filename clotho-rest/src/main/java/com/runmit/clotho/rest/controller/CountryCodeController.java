package com.runmit.clotho.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runmit.clotho.core.domain.CountryCode;
import com.runmit.clotho.core.service.CountryCodeService;
import com.runmit.clotho.rest.common.RestConst;
import com.runmit.clotho.rest.domain.CommonResp;
import com.runmit.clotho.rest.domain.RespCountryCode;

/**
 * @author zhipeng.tian
 * 
 * @date 2015年2月2日
 */
@RestController
@RequestMapping(value = "/countryCode")
public class CountryCodeController {
	private static final Logger LOGGER = LoggerFactory
            .getLogger(CountryCodeController.class);

    @Autowired
    private CountryCodeService codeService;
    
    /**
     * get country code list
     * @return
     */
    @RequestMapping(value = "/getlist")
    public CommonResp getList(){
    	CommonResp resp = new CommonResp();
    	try{
    		List<CountryCode> list = this.codeService.getList();
    		List<RespCountryCode> resplist = new ArrayList<RespCountryCode>();
    		for(CountryCode code:list){
    			RespCountryCode respcode = new RespCountryCode();
    			BeanUtils.copyProperties(code, respcode);
    			resplist.add(respcode);
    		}
    		resp.setData(resplist);
    		resp.setRtn(RestConst.RTN_OK);
    	}catch(Exception ex){
    		LOGGER.error("get country list error", ex);
    		resp.setRtn(RestConst.RTN_ERROR);
    	}
    	return resp;
    }
}
