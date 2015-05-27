package com.runmit.clotho.rest.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * @throws Exception 
     */
    @RequestMapping(value = "/3dboxcontroller")
    public void boxcontroller(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String ua = request.getHeader("User-Agent");
    	String waitImg = "http://medusa.d3dstore.com/app/dtapp/1-52-2-1.1-352219-9dfa883da29f998d58ef4f85f5b54970-1423467309012.jpg?appid=2&appkey=1.1&hwid=16&kv=1.0&bt=bt&ts=2057280121759&key=ffac9640cde44368c13a842daab2bbbe";
    	if(ua.indexOf("iPhone")>0){
    		response.sendRedirect("https://itunes.apple.com/us/app/3d-box/id958848828?ls=1&mt=8"); 
    	}else if(ua.indexOf("iPad")>0){
    		response.sendRedirect("https://itunes.apple.com/us/app/3d-box/id958848828?ls=1&mt=8"); 
    	}else if(ua.indexOf("Android")>0){
    		response.sendRedirect(waitImg); 
    	}else{
    		response.sendRedirect(waitImg); 
    	}
    }
}
