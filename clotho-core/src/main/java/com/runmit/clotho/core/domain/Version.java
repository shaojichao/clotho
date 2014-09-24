package com.runmit.clotho.core.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author Scott.Xie
 */
@Data
public class Version implements Serializable {

    private Integer id;
    private String version;
    private String serialno;
    private String memo;
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPkgurl() {
		return pkgurl;
	}
	public void setPkgurl(String pkgurl) {
		this.pkgurl = pkgurl;
	}
	public Integer getClientid() {
		return clientid;
	}
	public void setClientid(Integer clientid) {
		this.clientid = clientid;
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
	public void setUpgradetype(Integer upgradetype) {
		this.upgradetype = upgradetype;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdateby() {
		return updateby;
	}
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	private String pkgurl;
    private Integer clientid;//客户端类型
    private Integer showtype;//是否弹出提示:1-不弹出提示,2-弹出提示
    private Integer upgradetype;//依赖升级类型:1-可选升级,2-强制升级
    private String createby;
    private String createtime;
    private String updateby;
    private String updatetime;

}