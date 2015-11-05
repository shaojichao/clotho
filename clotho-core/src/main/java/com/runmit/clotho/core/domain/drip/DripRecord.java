package com.runmit.clotho.core.domain.drip;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


/**
 * @author zhipeng.tian
 * 
 * @date 2015年11月3日
 */
@Data
public class DripRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 338094758030889031L;
	private long id;
	private int uid;
	private String account;
	private int amount;
	private String code;
	private Date createtime;
}
