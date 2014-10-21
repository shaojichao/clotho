package com.runmit.clotho.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runmit.clotho.core.domain.admin.Admin;
import com.runmit.clotho.core.domain.admin.AdminRole;
import com.runmit.clotho.core.domain.admin.AdminRoleMember;
import com.runmit.clotho.core.domain.admin.RoleMenuMember;
import com.runmit.clotho.core.mapper.AdminMapper;
import com.runmit.clotho.log.domain.OpLog.OpType;
import com.runmit.clotho.log.service.OpLogService;

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
	@Autowired
	private OpLogService opLogService;
	
	public List<AdminRole> getRoleList(){
		return this.adminMapper.getRoleList();
	}
	
	public List<AdminRole> getActiveRoleList(){
		return this.adminMapper.getActiveRoleList();
	}
	
	public List<Admin> getAdminList(){
    	return this.adminMapper.getAdminList();
    }
	
	public Admin getAdminByUid(String uid){
    	return this.adminMapper.getAdminByUid(uid);
    }
	
	public List<AdminRole> getAdminRoleList(int adminid){
    	return this.adminMapper.getAdminRoleList(adminid);
    }
	
	@Transactional(readOnly=false)
	public void saveAdmin(Admin admin){
		this.adminMapper.addAdmin(admin);
		opLogService.saveObj(admin, OpType.INSERT, "admin", "clotho", admin.getCreateby());
	}
	
	@Transactional(readOnly=false)
	public void delAdminRole(int id,String adminName){
		this.adminMapper.delAdminRole(id);
	}
	
	@Transactional(readOnly=false)
	public void saveAdminRoleMember(AdminRoleMember adminRoleMember){
		this.adminMapper.addAdminRoleMember(adminRoleMember);
	}
	
	@Transactional(readOnly=false)
	public void saveRoleMenuMember(RoleMenuMember member){
		this.adminMapper.addRoleMenuMember(member);
	}
	
	@Transactional(readOnly=false)
	public void delRoleMenuMember(int id){
		this.adminMapper.delRoleMenuMember(id);
	}
	
	@Transactional(readOnly=false)
	public void saveAdminRole(AdminRole role){
		if(null==role.getId()||role.getId()==0){
			this.adminMapper.addAdminRole(role);
			opLogService.saveObj(role, OpType.INSERT, "admin", "clotho", role.getCreateby());
		}else{
			this.adminMapper.updateAdminRole(role);
		}
	}
}
