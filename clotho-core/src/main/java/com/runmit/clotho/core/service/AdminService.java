package com.runmit.clotho.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
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
@Component
public class AdminService {
	@Autowired
	private AdminMapper adminMapper;
//  @Autowired
	@Reference(version="1.0.0")
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
		opLogService.saveObj(this.adminMapper.getAdminRoleMember(id), OpType.DELETE, "role", "clotho", adminName);
		this.adminMapper.delAdminRole(id);
	}
	
	@Transactional(readOnly=false)
	public void saveAdminRoleMember(AdminRoleMember adminRoleMember){
		opLogService.saveObj(adminRoleMember, OpType.INSERT, "role", "clotho", adminRoleMember.getCreateby());
		this.adminMapper.addAdminRoleMember(adminRoleMember);
	}
	
	@Transactional(readOnly=false)
	public void saveRoleMenuMember(RoleMenuMember member){
		opLogService.saveObj(member, OpType.INSERT, "role", "clotho", member.getCreateby());
		this.adminMapper.addRoleMenuMember(member);
	}
	
	@Transactional(readOnly=false)
	public void delRoleMenuMember(int id,String adminName){
		opLogService.saveObj(this.adminMapper.getRoleMenuMember(id), OpType.DELETE, "role", "clotho", adminName);
		this.adminMapper.delRoleMenuMember(id);
	}
	
	@Transactional(readOnly=false)
	public void saveAdminRole(AdminRole role){
		if(null==role.getId()||role.getId()==0){
			this.adminMapper.addAdminRole(role);
			opLogService.saveObj(role, OpType.INSERT, "role", "clotho", role.getCreateby());
		}else{
			AdminRole temp = this.adminMapper.getAdminRole(role.getId());
			this.adminMapper.updateAdminRole(role);
			opLogService.updateObj(temp, role, OpType.UPDATE, "role", "clotho", role.getUpdateby());
		}
	}
}
