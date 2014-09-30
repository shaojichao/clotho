package com.runmit.clotho.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runmit.clotho.core.domain.admin.Admin;
import com.runmit.clotho.core.domain.admin.AdminRole;
import com.runmit.clotho.core.domain.admin.AdminRoleMember;
import com.runmit.clotho.core.mapper.AdminMapper;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月29日
 */
@Service
@Transactional(readOnly=true)
public class AdminService {
	@Autowired
	private AdminMapper adminMapper;
	
	public List<AdminRole> getRoleList(){
		return this.adminMapper.getRoleList();
	}
	
	public List<Admin> getAdminList(){
    	return this.adminMapper.getAdminList();
    }
	
	public List<AdminRole> getAdminRoleList(int adminid){
    	return this.adminMapper.getAdminRoleList(adminid);
    }
	
	@Transactional(readOnly=false)
	public void saveAdmin(Admin admin){
		this.adminMapper.addAdmin(admin);
	}
	
	@Transactional(readOnly=false)
	public void delAdminRole(int id){
		this.adminMapper.delAdminRole(id);
	}
	
	@Transactional(readOnly=false)
	public void saveAdminRoleMember(AdminRoleMember adminRoleMember){
		this.adminMapper.addAdminRoleMember(adminRoleMember);
	}
}
