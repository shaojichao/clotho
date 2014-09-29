package com.runmit.clotho.management.service;

import java.io.File;
import java.io.Writer;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.runmit.clotho.core.util.FileUtils;


/**
 * @author zhipeng.tian
 * 
 * 2014年9月29日
 */
@Service
public class UploadService {
	private static final Logger log = LoggerFactory
            .getLogger(UploadService.class);
	
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@Value("${file.download.url}")
	private String downloadUrl;
	
	public void uploadFile(MultipartFile file,HttpServletResponse response){
		try{
			String result = null;
	        Writer writer = response.getWriter();
	        if (file.getSize() <= 0) {
	            writer.write("{'success': false,'msg': 'uploadfailed'}");
	            writer.close();
	            writer.flush();
	            return;
	        }
	
	        try {
	        	log.info("/upload/upload.do Upload  file {} ...", file.getName());
	            FileUtils.ensureDirExist(uploadPath);
	            
	            // 将文件名进行改变，以防止覆盖
	            String fileName = UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename();
	            
	            file.transferTo(new File(uploadPath + fileName));
	            
	            result = downloadUrl + fileName;
	
	        } catch (Exception ex) {
	            log.error("Upload files in folder {} failure", file.getOriginalFilename(), ex);
	            writer.write("{'success': false,'msg': 'uploadfailed'}");
	            writer.close();
	            writer.flush();
	            return;
	        }
	        response.setContentType("text/html; charset=utf-8");
	    	
	    	writer.write("{'success': true, 'msg': '" + result + "'}");
	    	writer.close();
	    	writer.flush();
		}catch(Exception ex){
			log.error("upload error",ex);
		}
	}
}
