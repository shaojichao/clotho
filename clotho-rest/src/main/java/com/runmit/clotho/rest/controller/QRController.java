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
@RequestMapping(value = "/qr")
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
    public String qrRedirect(HttpServletRequest request){
    	String ua = request.getHeader("User-Agent");
    	if(ua.indexOf("iPhone")>0){
    		return "redirect:http://192.168.20.127/cloud_file/20150415/1dc0f4b524174914b028061e54c31f31.jpg?reqtype=1&userId=18&key=da850843179fdf9e0a04b28e51b90f31"; 
    	}else if(ua.indexOf("iPad")>0){
    		return "redirect:http://www.baidu.com"; 
    	}else if(ua.indexOf("Android")>0){
    		return "redirect:http://www.baidu.com"; 
    	}else{
    		return "redirect:http://www.baidu.com"; 
    	}
    }
}
