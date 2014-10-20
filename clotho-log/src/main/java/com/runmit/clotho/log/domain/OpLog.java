package com.runmit.clotho.log.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月26日
 */

public class OpLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8338156643493508592L;
	
	public enum OpType {
		INSERT("insert obj"),
		UPDATE("update obj"),
		DELETE("delete obj"),
		SELECT("select obj");
		
		private String context;
		public String getContext(){
			return this.context;
		}
		
		private OpType(String context){
			this.context = context;
		}
	}
	
	private Long id;
	private String content;
	private int systemId;
	private OpType opType;
	private String opMod;
	private String createby;
	private Date createtime;
	
	public OpLog(){
		
	}
	
	public OpLog(String content,String createby,OpType opType,int systemId,String opMod){
		this.content = content;
		this.createby = createby;
		this.systemId = systemId;
		this.opType = opType;
		this.opMod = opMod;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	public OpType getOpType() {
		return opType;
	}

	public void setOpType(OpType opType) {
		this.opType = opType;
	}

	public String getOpMod() {
		return opMod;
	}

	public void setOpMod(String opMod) {
		this.opMod = opMod;
	}
}
