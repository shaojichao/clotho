package com.runmit.clotho.management.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.runmit.clotho.management.domain.account.Admin;
import com.runmit.clotho.management.security.SecurityConstant;

/**
 * @author zhipeng.tian
 *
 *2014年9月24日
 * 
 */
@Controller
@Component
public class RedirectController {
	private static final Logger log = LoggerFactory
            .getLogger(RedirectController.class);
	
	@RequestMapping(value = "/index.do")
	public String toIndex(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute(SecurityConstant.ADMIN_SESSION_ATTRIBUTE);
		if(null == admin){
			return "login";
		}else{
			log.info("login to index.do");
			return "index";
		}
	}
	
	@RequestMapping(value = "/userfeedback.do")
	public String toUserfeedback() {
		return "userfeedback";
	}
}
