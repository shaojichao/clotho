package com.runmit.clotho.core.domain.admin;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月29日
 */

public class AdminRole {
	private int id;
	private String name; // 角色名
    private String description; // 角色描述
    private CurrentStatus status;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CurrentStatus getStatus() {
		return status;
	}
	public void setStatus(CurrentStatus status) {
		this.status = status;
	}
}
