package com.runmit.clotho.management.domain;

import lombok.Data;

/**
 * @author zhipeng.tian
 * 
 * @date 2015年2月5日
 */
@Data
public class CDNBackRes {
	private int taskId;
	private String url;
	private int status;
	private String desc;
	private String callbackContext;
}
