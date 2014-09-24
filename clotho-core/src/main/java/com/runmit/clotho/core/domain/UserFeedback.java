package com.runmit.clotho.core.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author Scott.Xie
 */
@Data
public class UserFeedback implements Serializable {
    private Integer id;
    private String hwid;
    private String udid;
    private String wifimac;
    private String wirelesssmac;
    private String wiremac;
    private Integer os;
    private String osver;
    private Integer device;
    private String area;
    private String language;
    private String imei;
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHwid() {
		return hwid;
	}
	public void setHwid(String hwid) {
		this.hwid = hwid;
	}
	public String getUdid() {
		return udid;
	}
	public void setUdid(String udid) {
		this.udid = udid;
	}
	public String getWifimac() {
		return wifimac;
	}
	public void setWifimac(String wifimac) {
		this.wifimac = wifimac;
	}
	public String getWirelesssmac() {
		return wirelesssmac;
	}
	public void setWirelesssmac(String wirelesssmac) {
		this.wirelesssmac = wirelesssmac;
	}
	public String getWiremac() {
		return wiremac;
	}
	public void setWiremac(String wiremac) {
		this.wiremac = wiremac;
	}
	public Integer getOs() {
		return os;
	}
	public void setOs(Integer os) {
		this.os = os;
	}
	public String getOsver() {
		return osver;
	}
	public void setOsver(String osver) {
		this.osver = osver;
	}
	public Integer getDevice() {
		return device;
	}
	public void setDevice(Integer device) {
		this.device = device;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getIdfv() {
		return idfv;
	}
	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getAppver() {
		return appver;
	}
	public void setAppver(String appver) {
		this.appver = appver;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDevicebrand() {
		return devicebrand;
	}
	public void setDevicebrand(String devicebrand) {
		this.devicebrand = devicebrand;
	}
	public String getDevicedevice() {
		return devicedevice;
	}
	public void setDevicedevice(String devicedevice) {
		this.devicedevice = devicedevice;
	}
	public String getDevicemodel() {
		return devicemodel;
	}
	public void setDevicemodel(String devicemodel) {
		this.devicemodel = devicemodel;
	}
	public String getDevicehardware() {
		return devicehardware;
	}
	public void setDevicehardware(String devicehardware) {
		this.devicehardware = devicehardware;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getDeviceserial() {
		return deviceserial;
	}
	public void setDeviceserial(String deviceserial) {
		this.deviceserial = deviceserial;
	}
	public String getRo() {
		return ro;
	}
	public void setRo(String ro) {
		this.ro = ro;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Long getDts() {
		return dts;
	}
	public void setDts(Long dts) {
		this.dts = dts;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	private String idfv;
    private String appkey;
    private String appver;
    private String uid;
    private String devicebrand;
    private String devicedevice;
    private String devicemodel;
    private String devicehardware;
    private String deviceid;
    private String deviceserial;
    private String ro;
    private String channel;
    private Long dts;
    private String contact;
    private String content;
}