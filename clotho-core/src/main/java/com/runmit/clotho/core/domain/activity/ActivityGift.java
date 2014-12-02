package com.runmit.clotho.core.domain.activity;

import java.io.Serializable;

/**
 * @author zhipeng.tian
 * 
 * 2014年12月2日
 */

public class ActivityGift implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3127732304999366294L;
	private Integer id;
	private Integer activityId;
	private Integer giftId;
	private String giftName;
	private Integer giftNum;
	private String adminName;
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public Integer getGiftNum() {
		return giftNum;
	}
	public void setGiftNum(Integer giftNum) {
		this.giftNum = giftNum;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getGiftId() {
		return giftId;
	}
	public void setGiftId(Integer giftId) {
		this.giftId = giftId;
	}
	public String getGiftName() {
		return giftName;
	}
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	
	

}
