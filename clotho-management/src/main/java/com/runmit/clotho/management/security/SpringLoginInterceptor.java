package com.runmit.clotho.management.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月29日
 */

public class SpringLoginInterceptor extends HandlerInterceptorAdapter{
	private String mappingURL =".*/.*\\.do" ;
	private String[] ignoreUrls = {"upload/upload.do","upgrade/cdnback.do"};
	
	private static final Logger LOGGER = LoggerFactory
            .getLogger(SpringLoginInterceptor.class);
	
	@SuppressWarnings("unused")
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) {
		
		String className = handler.getClass().getName();// packageName.ClassName
		String url= req.getRequestURL().toString();     
	    HttpSession session = req.getSession();
	    try {
	    	for(String iUrl:ignoreUrls){
	    		if(url.indexOf(iUrl)>0){
	    			return true;
	    		}
	    	}
		    if(url.matches(mappingURL)){
		    	
		    	if(session.getAttribute(SecurityConstant.ADMIN_SESSION_ATTRIBUTE) == null){
		    		
		    		if(req.getHeader("x-requested-with")!=null && req.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){ 
			    		resp.setHeader("sessionstatus","timeout"); 
	    			} 
					req.getRequestDispatcher("/login.jsp").forward(req, resp);
					return false;
			    }
		    }
	    } catch (ServletException e) {
	    	LOGGER.error("ServletException",e);
		} catch (IOException e) {
			LOGGER.error("IOException",e);
		}  
	    return true;
	}
}
