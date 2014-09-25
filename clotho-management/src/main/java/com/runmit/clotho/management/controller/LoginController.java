package com.runmit.clotho.management.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runmit.clotho.core.dto.ExtStatusEntity;
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
public class LoginController {
	private static final Logger log = LoggerFactory
            .getLogger(LoginController.class);
	
	@RequestMapping(value = "/loginValid")
	public @ResponseBody ExtStatusEntity userLogin(HttpServletRequest request, Model model) {
		ExtStatusEntity resp = new ExtStatusEntity();
		Admin admin = new Admin();
		admin.setId(1);
		admin.setName("I am a tester");
		HttpSession session = request.getSession();
		session.setAttribute(SecurityConstant.ADMIN_SESSION_ATTRIBUTE, admin);
		resp.setMsg("success");;
		resp.setSuccess(true);
		log.info(admin.getName()+" login");
		return resp;
	}
}
