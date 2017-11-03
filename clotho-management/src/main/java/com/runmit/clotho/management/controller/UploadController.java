package com.runmit.clotho.management.controller;

import com.runmit.clotho.management.service.COSService;
import com.runmit.clotho.management.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月28日
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
	private UploadService uploadService;

	@Autowired
	private COSService cosService;


	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public void uploadIcon(@RequestParam(value = "pkg") MultipartFile file, HttpServletResponse response, HttpServletRequest request) throws Exception {
//		ExtStatusEntity entity = new ExtStatusEntity();

		cosService.uploadFile(file,response,request);
		LOGGER.info("upload file");
//		if (data == null) {
//			entity.setMsg("上传腾讯云出错");
//			entity.setSuccess(false);
//			return entity;
//		}
//		String resource_path = data.getString("resource_path");
//		entity.setMsg(resource_path);
//		entity.setSuccess(true);
//		return entity;
	}

	/*@RequestMapping(value = "/upload.do",method=RequestMethod.POST)
    public void upload(@RequestParam(value = "pkg") MultipartFile file, 
    		HttpServletRequest request,HttpServletResponse response) throws Exception {
		this.uploadService.uploadFile(file, response);
		LOGGER.info("upload file");
    }*/

	/**
	 * 上传icon
	 * @param file
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	/*@RequestMapping(value = "/uploadIcon.do")
	public void uploadIcon(@RequestParam(value = "pkg") MultipartFile file,HttpServletResponse response, HttpServletRequest request) throws Exception {
		this.uploadService.uploadIcon(file,response);
	}*/

}
