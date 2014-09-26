package com.runmit.clotho.core.domain.admin;

import java.io.Serializable;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月26日
 */

public class Admin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8338156643493508592L;
	private int id;
	private String name;
	private String mail;
	private String ename;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
}
