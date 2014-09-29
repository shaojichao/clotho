package com.runmit.clotho.core.domain.upgrade;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Scott.Xie
 */
@Data
public class UpgradePlan implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7811102660151073545L;
	private String version;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	private Integer id;
    private String originid;
    private String upgradeid;
    private Integer clientid;
    private String memo;
    private Integer showtype;
    private Integer upgradetype;
    private String createby;
    private String updateby;
    private Date createtime;
    private Date updatetime;
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOriginid() {
		return originid;
	}
	public void setOriginid(String originid) {
		this.originid = originid;
	}
	public String getUpgradeid() {
		return upgradeid;
	}
	public void setUpgradeid(String upgradeid) {
		this.upgradeid = upgradeid;
	}
	public Integer getClientid() {
		return clientid;
	}
	public void setClientid(Integer clientid) {
		this.clientid = clientid;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Integer getShowtype() {
		return showtype;
	}
	public void setShowtype(Integer showtype) {
		this.showtype = showtype;
	}
	public Integer getUpgradetype() {
		return upgradetype;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public void setUpgradetype(Integer upgradetype) {
		this.upgradetype = upgradetype;
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