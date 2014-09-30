package com.runmit.clotho.management.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runmit.clotho.core.domain.admin.Admin;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.management.security.LDAPValidation;
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
	
	@Autowired
	private LDAPValidation ldap;
	
	/**
	 * user info checked
	 * @param request
	 * @param model
	 * @param uid
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/loginValid")
	public @ResponseBody ExtStatusEntity userLogin(HttpServletRequest request, Model model,
			@RequestParam("name")String uid,@RequestParam("password")String password) {
		ExtStatusEntity resp = new ExtStatusEntity();
		
		Admin admin = ldap.validAdmin(uid, password);
		if(null==admin){
//			resp.setMsg("success");
//			resp.setSuccess(false);
			//接入正式系统前使用
			admin = new Admin();
			admin.setId(0);
			admin.setName("我是访客");
			resp.setMsg("success");
			resp.setSuccess(true);
			HttpSession session = request.getSession();
			session.setAttribute(SecurityConstant.ADMIN_SESSION_ATTRIBUTE, admin);
		}else{
			HttpSession session = request.getSession();
			session.setAttribute(SecurityConstant.ADMIN_SESSION_ATTRIBUTE, admin);
			resp.setMsg("success");
			resp.setSuccess(true);
			log.info(admin.getName()+" login");
		}
		return resp;
	}
	
	/**
	 * logout
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String userLogout(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.removeAttribute(SecurityConstant.ADMIN_SESSION_ATTRIBUTE);
		return "index";
	}
}
