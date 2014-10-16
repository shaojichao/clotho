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
import com.runmit.clotho.core.service.AdminService;
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
	private static final Logger LOGGER = LoggerFactory
            .getLogger(LoginController.class);
	
	@Autowired
	private LDAPValidation ldap;
	@Autowired
	private AdminService adminService;
	
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
		Admin a = this.adminService.getAdminByUid(uid);
		if(null==admin){
			resp.setMsg("LDAP账号密码错误或者不存在");
			resp.setSuccess(false);
		}else if(null==a){
			resp.setMsg("后台管理账号不存在");
			resp.setSuccess(false);
		}else{
			admin.setId(a.getId());
			HttpSession session = request.getSession();
			session.setAttribute(SecurityConstant.ADMIN_SESSION_ATTRIBUTE, admin);
			resp.setMsg("success");
			resp.setSuccess(true);
			LOGGER.info(admin.getName()+" login");
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
