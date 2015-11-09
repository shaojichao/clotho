package com.runmit.clotho.core.domain.drip;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author zhipeng.tian
 * 
 * @date 2015年11月9日
 */
@Data
public class ActivationCode implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 7047426810910298156L;
	private long id;
	private String code;
	private int status;
	private Date dateEnd;
	private Date createtime;
	private String createby;
	
}
