package com.runmit.clotho.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.runmit.clotho.core.domain.admin.Admin;
import com.runmit.clotho.core.domain.admin.AdminRole;
import com.runmit.clotho.core.domain.admin.AdminRoleMember;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月29日
 */

public interface AdminMapper {
	@Select("SELECT * FROM AdminRole")
    List<AdminRole> getRoleList();
	
	@Select("SELECT * FROM Admin")
    List<Admin> getAdminList();
	
	@Select("SELECT distinct a.* FROM AdminRole a, AdminRoleMember c where a.id = c.roleid and c.adminid=#{adminid}")
    List<AdminRole> getAdminRoleList(@Param("adminid")int adminid);
	
	@Insert("insert into Admin (uid) values (#{uid})")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addAdmin(Admin admin);
	
	@Delete("delete from AdminRoleMember where roleid=#{roleid}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "roleid")
	void delAdminRole(@Param("roleid")int roleid);
	
	@Insert("insert into AdminRoleMember (adminid,roleid) values (#{adminid},#{roleid})")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addAdminRoleMember(AdminRoleMember member);
}
