package com.runmit.clotho.core.domain.admin;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月29日
 */

public class RoleMenuMember {
	private int roleid;
	private int menuid;
	private String createby;
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public int getMenuid() {
		return menuid;
	}
	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
}
