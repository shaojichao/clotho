package com.runmit.clotho.management.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.runmit.clotho.core.domain.admin.Admin;

/**
 * @author zhipeng.tian
 * 
 * 2014年10月21日
 */

public class SessionUtil {
	public static Admin getLoginAdmin(HttpServletRequest request){
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute(SecurityConstant.ADMIN_SESSION_ATTRIBUTE);
		return admin;
	}
	
	public static String getLoginAdminName(HttpServletRequest request){
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute(SecurityConstant.ADMIN_SESSION_ATTRIBUTE);
		return admin.getName();
	}
	
	public static String getLoginAdminUid(HttpServletRequest request){
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute(SecurityConstant.ADMIN_SESSION_ATTRIBUTE);
		return admin.getUid();
	}
	
	public static void setLoginAdmin(HttpServletRequest request,Admin admin){
		HttpSession session = request.getSession();
		session.setAttribute(SecurityConstant.ADMIN_SESSION_ATTRIBUTE, admin);
	}

	/**
	 * 获取登陆员ID
	 * @param request
	 * @return
	 */
	public static int getLoginAdminId(HttpServletRequest request){
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute(SecurityConstant.ADMIN_SESSION_ATTRIBUTE);
		return admin.getId();
	}
}
