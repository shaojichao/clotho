package com.runmit.clotho.management.controller;

import java.io.File;
import java.io.Writer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.runmit.clotho.core.util.FileUtils;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月28日
 */
@Controller
@Component
@RequestMapping(value = "/upload")
public class UploadController {
	
	private static final Logger log = LoggerFactory
            .getLogger(UploadController.class);
	
	/*@Value("${file.upload.path}")
	private String uploadPath;*/
	
	@RequestMapping(value = "/upload.do",method=RequestMethod.POST)
    public void upload(@RequestParam(value = "pkg") MultipartFile file, 
    		HttpServletRequest request,HttpServletResponse response) throws Exception {
       
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
            // put at temp place to do the verify
        	String p = request.getSession().getServletContext().getRealPath("/");
        	p = p.substring(0, p.indexOf("webapps")+7);
            String tmpDir = p + File.separator + "upload" + File.separator + "tmp" + File.separator;
            FileUtils.ensureDirExist(tmpDir);
            
            // 将文件名进行改变，以防止覆盖
            String fileName = UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename();
            
            file.transferTo(new File(tmpDir + fileName));
            
            result = fileName;

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
    }
}
