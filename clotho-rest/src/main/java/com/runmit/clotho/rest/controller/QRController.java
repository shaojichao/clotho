package com.runmit.clotho.rest.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${qr.3dboxcontroller.ios}")
	private String qr3dboxcontrollerIOS;
	@Value("${qr.3dboxcontroller.android}")
	private String qr3dboxcontrollerANDROID;
    /**
     * get country code list
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/3dboxcontroller")
    public void boxcontroller(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String ua = request.getHeader("User-Agent");
    	if(ua.indexOf("iPhone")>0){
    		response.sendRedirect(qr3dboxcontrollerIOS); 
    	}else if(ua.indexOf("iPad")>0){
    		response.sendRedirect(qr3dboxcontrollerIOS); 
    	}else if(ua.indexOf("Android")>0){
    		response.sendRedirect(qr3dboxcontrollerANDROID); 
    	}else{
    		response.sendRedirect(qr3dboxcontrollerANDROID); 
    	}
    }
}
