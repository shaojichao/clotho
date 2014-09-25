package com.runmit.clotho.core.dto;
/**
 * @author zhipeng.tian
 *
 *2014年9月24日
 * 
 */
public class ExtStatusEntity {
	private boolean success = false;
	private String msg;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}	
