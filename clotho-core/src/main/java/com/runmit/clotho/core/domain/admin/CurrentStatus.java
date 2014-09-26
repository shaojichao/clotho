package com.runmit.clotho.core.domain.admin;
/**
 * @author zhipeng.tian
 *
 *2014年9月24日
 * 
 */
public enum CurrentStatus {
	ACTIVE("ACTIVE"),
	INACTIVE("INACTIVE"),
	LOGICAL_DELETED("LOGICAL_DETELTED");
	private final String status;
	
	private CurrentStatus(String status){
		this.status = status;
	}
	
	public String value(){
		return status;
	}
}
