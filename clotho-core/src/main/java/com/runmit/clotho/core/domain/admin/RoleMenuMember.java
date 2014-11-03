package com.runmit.clotho.core.domain.admin;

import java.io.Serializable;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月29日
 */

public class RoleMenuMember implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4298387310795633816L;
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
