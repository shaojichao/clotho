package com.runmit.clotho.management.domain;

import java.util.Date;
import java.util.List;

import com.runmit.clotho.core.domain.admin.CurrentStatus;
import com.runmit.clotho.core.domain.admin.Menu;

/**
 * @author zhipeng.tian
 * 
 * 2014年10月8日
 */

public class MenuDTO{
	private String cls="folder";
	private boolean expanded=true;
	private boolean checked=false;
	private Integer id;
	private String text;
	private int parentID;
	private String parentName;
	private Boolean leaf;
	private List<MenuDTO> children;
	
	public List<MenuDTO> getChildren() {
		return children;
	}
	public void setChildren(List<MenuDTO> children) {
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
	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
