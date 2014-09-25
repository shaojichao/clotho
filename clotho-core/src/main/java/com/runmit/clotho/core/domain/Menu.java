package com.runmit.clotho.core.domain;

import java.io.Serializable;

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
	private int id;
	private String text;
	private int parentID;
	private String url;
	private Boolean leaf;
	private CurrentStatus status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	
}
