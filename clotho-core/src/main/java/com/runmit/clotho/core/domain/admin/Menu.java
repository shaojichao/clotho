package com.runmit.clotho.core.domain.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zhipeng.tian
 *
 *2014年9月24日
 * 
 */
public class Menu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8437820376336922873L;
	private Integer id;
	private String text;
	private int parentID;
	private String parentName;
	private String url;
	private Boolean leaf;
	private CurrentStatus status;
	private Date createTime;
	private String createdBy;
	private Integer roleid;
	private Integer orderNum;
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	private List<Menu> children;
	
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getParentID() {
		return parentID;
	}
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	public CurrentStatus getStatus() {
		return status;
	}
	public void setStatus(CurrentStatus status) {
		this.status = status;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
}
