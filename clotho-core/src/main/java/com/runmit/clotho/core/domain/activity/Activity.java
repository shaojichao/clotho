package com.runmit.clotho.core.domain.activity;

import java.io.Serializable;
import java.util.Date;

import com.runmit.clotho.core.domain.BaseDomain;

/**
 * @author zhipeng.tian
 * 
 * 2014年12月2日
 */

public class Activity extends BaseDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8659192465757258633L;
	
	private String name;
	private String content;
	private int status;
	private Date dateBegin;
	private String dateBeginStr;
	private Date dateEnd;
	private String dateEndStr;
	private String channel;
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getDateBeginStr() {
		return dateBeginStr;
	}
	public void setDateBeginStr(String dateBeginStr) {
		this.dateBeginStr = dateBeginStr;
	}
	public String getDateEndStr() {
		return dateEndStr;
	}
	public void setDateEndStr(String dateEndStr) {
		this.dateEndStr = dateEndStr;
	}
	private int timeLimit;
	private int scoreLimit;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getDateBegin() {
		return dateBegin;
	}
	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	public int getScoreLimit() {
		return scoreLimit;
	}
	public void setScoreLimit(int scoreLimit) {
		this.scoreLimit = scoreLimit;
	}

}
