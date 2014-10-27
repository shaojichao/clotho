package com.runmit.clotho.core.domain.admin;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月29日
 */

public class AdminRoleMember {
	private int adminid;
	private int roleid;
	private String createby;
	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
}
