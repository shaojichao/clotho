package com.runmit.clotho.management.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.runmit.clotho.management.service.UploadService;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月28日
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController {
	
	private static final Logger LOGGER = LoggerFactory
            .getLogger(UploadController.class);
	
	@Autowired
	private UploadService uploadService;
	
	@RequestMapping(value = "/upload.do",method=RequestMethod.POST)
    public void upload(@RequestParam(value = "pkg") MultipartFile file, 
    		HttpServletRequest request,HttpServletResponse response) throws Exception {
		this.uploadService.uploadFile(file, response);
		LOGGER.info("upload file");
    }
}
