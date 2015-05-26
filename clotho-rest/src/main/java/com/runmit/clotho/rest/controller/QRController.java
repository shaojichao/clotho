package com.runmit.clotho.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runmit.clotho.core.service.CountryCodeService;

/**
 * @author zhipeng.tian
 * 
 * @date 2015年2月2日
 */
@RestController
@RequestMapping(value = "/QR")
public class QRController {
	private static final Logger LOGGER = LoggerFactory
            .getLogger(QRController.class);

    @Autowired
    private CountryCodeService codeService;
    
    /**
     * get country code list
     * @return
     */
    @RequestMapping(value = "/test")
    public void test(HttpServletRequest request){
    	String ua = request.getHeader("User-Agent");
    	LOGGER.info("ua======="+ua);
    	System.out.println(ua);
    	
    }
}
