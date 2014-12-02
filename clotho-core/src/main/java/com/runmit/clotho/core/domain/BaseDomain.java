package com.runmit.clotho.core.domain;

import java.util.Date;

/**
 * @author zhipeng.tian
 * 
 * 2014年10月20日
 */
public class BaseDomain{
	
	private Integer id;
	private Date createtime;
	private String createby;
	private Date updatetime;
	private String updateby;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getUpdateby() {
		return updateby;
	}
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
}
