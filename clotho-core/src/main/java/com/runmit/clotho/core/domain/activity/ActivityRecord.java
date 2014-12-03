package com.runmit.clotho.core.domain.activity;

import java.io.Serializable;

import com.runmit.clotho.core.domain.BaseDomain;

/**
 * @author zhipeng.tian
 * 
 * 2014年12月3日
 */

public class ActivityRecord extends BaseDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8336353142843282052L;
	
	private int uid;
	private String username;
	private int activityId;
	private String activityName;
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

}
