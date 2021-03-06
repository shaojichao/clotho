package com.runmit.clotho.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.runmit.clotho.core.domain.admin.Admin;
import com.runmit.clotho.core.domain.admin.AdminRole;
import com.runmit.clotho.core.domain.admin.AdminRoleMember;
import com.runmit.clotho.core.domain.admin.RoleMenuMember;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月29日
 */

public interface AdminMapper {
	@Select("SELECT * FROM AdminRole")
    List<AdminRole> getRoleList();
	
	@Select("SELECT * FROM AdminRole where status='ACTIVE'")
    List<AdminRole> getActiveRoleList();
	
	@Select("SELECT * FROM Admin")
    List<Admin> getAdminList();
	
	@Select("SELECT * FROM Admin where uid=#{uid}")
    Admin getAdminByUid(@Param("uid")String uid);
	
	@Select("SELECT distinct a.* FROM AdminRole a, AdminRoleMember c where a.id = c.roleid and c.adminid=#{adminid}")
    List<AdminRole> getAdminRoleList(@Param("adminid")int adminid);
	
	@Insert("insert into Admin (uid) values (#{uid})")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addAdmin(Admin admin);
	
	@Delete("delete from AdminRoleMember where roleid=#{roleid} AND adminid=#{uid}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "roleid")
	void delAdminRole(@Param("roleid")int roleid,@Param("uid")int uid);
	
	@Select("select * from AdminRoleMember where roleid=#{roleid}")
	List<AdminRoleMember> getAdminRoleMember(@Param("roleid")int roleid);
	
	@Insert("insert into AdminRoleMember (adminid,roleid) values (#{adminid},#{roleid})")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "adminid,roleid")
    void addAdminRoleMember(AdminRoleMember member);
	
	@Insert("insert into AdminRole (name,description,status) values (#{name},#{description},#{status})")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addAdminRole(AdminRole role);
	
	@Update("update AdminRole set name=#{name},description=#{description},status=#{status} where id=#{id}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void updateAdminRole(AdminRole role);
	
	@Select("select * from AdminRole where id=#{id}")
	AdminRole getAdminRole(@Param("id")int id);
	
	@Delete("delete from RoleMenuMember where roleid=#{roleid}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "roleid")
	void delRoleMenuMember(@Param("roleid")int roleid);
	
	@Select("select * from RoleMenuMember where roleid=#{roleid}")
	List<RoleMenuMember> getRoleMenuMember(@Param("roleid")int roleid);
	
	@Insert("insert into RoleMenuMember (roleid,menuid) values (#{roleid},#{menuid})")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "roleid,menuid")
    void addRoleMenuMember(RoleMenuMember member);
	
	
}
