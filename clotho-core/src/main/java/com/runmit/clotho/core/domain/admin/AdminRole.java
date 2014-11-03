package com.runmit.clotho.core.domain.admin;

import java.io.Serializable;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月29日
 */

public class AdminRole implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7382967512136289316L;
	private Integer id;
	private String name; // 角色名
    private String description; // 角色描述
    private CurrentStatus status;
    private String createby;
    private String updateby;
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	public String getUpdateby() {
		return updateby;
	}
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
}
