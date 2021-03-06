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
import com.runmit.clotho.core.domain.admin.RoleMenuMember;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.AdminService;
import com.runmit.clotho.management.security.SessionUtil;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月30日
 */
@Controller
@Component
@RequestMapping(value = "/admin")
public class AdminController {
	private static final Logger LOGGER = LoggerFactory
            .getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "/adminlist.do")
	public @ResponseBody ExtEntity<Admin> getAdminList(){
		List<Admin> list = this.adminService.getAdminList();
		ExtEntity<Admin> entity = new ExtEntity<Admin>();
		entity.setResult(list.size());
		entity.setRows(list);
		LOGGER.info("getAdminList");
		return entity;
		
	}
	
	@RequestMapping(value = "/adminrole.do")
	public @ResponseBody ExtEntity<AdminRole> getAdminRoleListByAdminid(@RequestParam("adminid")Integer adminid){
		List<AdminRole> list = this.adminService.getAdminRoleList(adminid);
		ExtEntity<AdminRole> entity = new ExtEntity<AdminRole>();
		entity.setResult(list.size());
		entity.setRows(list);
		LOGGER.info("getAdminRoleListByAdminid");
		return entity;
		
	}
	
	@RequestMapping(value = "/roles.do")
	public @ResponseBody ExtEntity<AdminRole> getRoles(){
		List<AdminRole> list = this.adminService.getRoleList();
		ExtEntity<AdminRole> entity = new ExtEntity<AdminRole>();
		entity.setResult(list.size());
		entity.setRows(list);
		LOGGER.info("getRoles");
		return entity;
		
	}
	
	@RequestMapping(value = "/rolelist.do")
	public @ResponseBody List<AdminRole> getRoleList(){
		List<AdminRole> list = this.adminService.getActiveRoleList();
		LOGGER.info("getRoleList");
		return list;
		
	}
	
	@RequestMapping(value = "/saveAdmin.do",method=RequestMethod.POST)
	public @ResponseBody ExtStatusEntity saveAdmin(@RequestParam("uid")String uid,HttpServletRequest request){
		ExtStatusEntity result = new ExtStatusEntity();
		try{
			Admin admin = new Admin();
			admin.setUid(uid);
			admin.setCreateby(SessionUtil.getLoginAdminName(request));
			boolean addResult = this.adminService.saveAdmin(admin);
			if(!addResult){
				result.setMsg("已经添加过该账号");
				result.setSuccess(false);
			}else{
				result.setMsg("succeed");
				result.setSuccess(true);
			}
		}catch(Exception ex){
			LOGGER.error("save admin error",ex);
			result.setMsg("保存失败");
			result.setSuccess(false);
		}
		LOGGER.info("saveAdmin");
		return result;
		
	}
	
	@RequestMapping(value = "/saveAdminRoleMember.do",method=RequestMethod.POST)
	public @ResponseBody ExtStatusEntity saveAdminRoleMember(AdminRoleMember member,HttpServletRequest request){
		ExtStatusEntity result = new ExtStatusEntity();
		try{
			member.setCreateby(SessionUtil.getLoginAdminName(request));
			this.adminService.saveAdminRoleMember(member);
			result.setMsg("succeed");
			result.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("saveAdminRoleMember error",ex);
			result.setMsg("保存失败");
			result.setSuccess(false);
		}
		LOGGER.info("saveAdminRoleMember");
		return result;
		
	}
	
	@RequestMapping(value = "/delAdminRole.do")
	public @ResponseBody ExtStatusEntity delAdminRole(@RequestParam("id")int id,@RequestParam("userId")int userId,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		try{
			this.adminService.delAdminRole(id,SessionUtil.getLoginAdminName(request),userId);
			
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("delAdminRole error",ex);
			entity.setMsg("删除失败");
			entity.setSuccess(false);
		}
		
		LOGGER.info("delAdminRole");
		return entity;
	}
	
	@RequestMapping(value = "/saveRole.do",method=RequestMethod.POST)
	public @ResponseBody ExtStatusEntity saveRole(AdminRole role,HttpServletRequest request){
		ExtStatusEntity result = new ExtStatusEntity();
		try{
			if(null == role.getId()|| 0 == role.getId()){
				role.setCreateby(SessionUtil.getLoginAdminName(request));
			}else{
				role.setUpdateby(SessionUtil.getLoginAdminName(request));
			}
			this.adminService.saveAdminRole(role);
			result.setMsg("succeed");
			result.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("saveRole error",ex);
			result.setMsg("保存失败");
			result.setSuccess(false);
		}
		LOGGER.info("saveRole");
		return result;
		
	}
	
	@RequestMapping(value = "/saveRoleMenuMember.do",method=RequestMethod.POST)
	public @ResponseBody ExtStatusEntity saveAdminRoleMember(@RequestParam("roleID")int roleid,@RequestParam("menuIDArr")String menuIDArr,HttpServletRequest request){
		ExtStatusEntity result = new ExtStatusEntity();
		try{
			
			String[] menus = menuIDArr.split(",");
			this.adminService.delRoleMenuMember(roleid,SessionUtil.getLoginAdminName(request));
			for(String menu:menus){
				RoleMenuMember member = new RoleMenuMember();
				member.setRoleid(roleid);
				member.setMenuid(Integer.valueOf(menu));
				member.setCreateby(SessionUtil.getLoginAdminName(request));
				this.adminService.saveRoleMenuMember(member);
			}
			result.setMsg("succeed");
			result.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("saveAdminRoleMember error",ex);
			result.setMsg("保存失败");
			result.setSuccess(false);
		}
		LOGGER.info("saveAdminRoleMember");
		return result;
		
	}
}
