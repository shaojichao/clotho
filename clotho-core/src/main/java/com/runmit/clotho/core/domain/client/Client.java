package com.runmit.clotho.core.domain.client;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author zhipeng.tian
 * 
 * 2014年10月20日
 */
@Data
public class Client implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6447429102510683302L;
	private Integer clientId;
	private String name;
    private Integer superProjectId;
	private String description;
	private Date createtime;
	private String createby;
	private Date updatetime;
	private String updateby;
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    public Integer getSuperProjectId() {
        return superProjectId;
    }
    public void setSuperProjectId(Integer superProjectId) {
        this.superProjectId = superProjectId;
    }
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
