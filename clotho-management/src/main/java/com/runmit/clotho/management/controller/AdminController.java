package com.runmit.clotho.management.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runmit.clotho.core.domain.admin.Admin;
import com.runmit.clotho.core.domain.admin.AdminRole;
import com.runmit.clotho.core.domain.admin.AdminRoleMember;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.AdminService;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月30日
 */
@Controller
@Component
@RequestMapping(value = "/admin")
public class AdminController {
	private static final Logger log = LoggerFactory
            .getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "/adminlist.do")
	public @ResponseBody ExtEntity<Admin> getAdminList(){
		List<Admin> list = this.adminService.getAdminList();
		ExtEntity<Admin> entity = new ExtEntity<Admin>();
		entity.setResult(list.size());
		entity.setRows(list);
		log.info("getAdminList");
		return entity;
		
	}
	
	@RequestMapping(value = "/adminrole.do")
	public @ResponseBody ExtEntity<AdminRole> getAdminRoleListByAdminid(@RequestParam("adminid")Integer adminid){
		List<AdminRole> list = this.adminService.getAdminRoleList(adminid);
		ExtEntity<AdminRole> entity = new ExtEntity<AdminRole>();
		entity.setResult(list.size());
		entity.setRows(list);
		log.info("getAdminRoleListByAdminid");
		return entity;
		
	}
	
	@RequestMapping(value = "/rolelist.do")
	public @ResponseBody List<AdminRole> getRoleList(){
		List<AdminRole> list = this.adminService.getRoleList();
		log.info("getRoleList");
		return list;
		
	}
	
	@RequestMapping(value = "/saveAdmin.do",method=RequestMethod.POST)
	public @ResponseBody ExtStatusEntity saveAdmin(@RequestParam("uid")String uid){
		ExtStatusEntity result = new ExtStatusEntity();
		try{
			Admin admin = new Admin();
			admin.setUid(uid);
			this.adminService.saveAdmin(admin);
			result.setMsg("succeed");
			result.setSuccess(true);
		}catch(Exception ex){
			log.error("save admin error",ex);
			result.setMsg("保存失败");
			result.setSuccess(false);
		}
		log.info("saveAdmin");
		return result;
		
	}
	
	@RequestMapping(value = "/saveAdminRoleMember.do",method=RequestMethod.POST)
	public @ResponseBody ExtStatusEntity saveAdminRoleMember(AdminRoleMember member){
		ExtStatusEntity result = new ExtStatusEntity();
		try{
			this.adminService.saveAdminRoleMember(member);
			result.setMsg("succeed");
			result.setSuccess(true);
		}catch(Exception ex){
			log.error("saveAdminRoleMember error",ex);
			result.setMsg("保存失败");
			result.setSuccess(false);
		}
		log.info("saveAdminRoleMember");
		return result;
		
	}
	
	@RequestMapping(value = "/delAdminRole.do")
	public @ResponseBody ExtStatusEntity delAdminRole(@RequestParam("id")int id,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		try{
			this.adminService.delAdminRole(id);
			
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			log.error("delAdminRole error",ex);
			entity.setMsg("删除失败");
			entity.setSuccess(false);
		}
		
		log.info("delAdminRole");
		return entity;
	}
}
