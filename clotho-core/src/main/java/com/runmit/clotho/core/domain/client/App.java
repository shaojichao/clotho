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
public class App implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1614148187456402785L;
	private Integer appId;
	private String name;
	private String description;
	private Date createtime;
	private String createby;
	private Date updatetime;
	private String updateby;
}
