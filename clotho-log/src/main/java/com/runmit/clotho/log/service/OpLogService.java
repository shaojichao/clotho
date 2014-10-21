package com.runmit.clotho.log.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.runmit.clotho.log.domain.OpLog;
import com.runmit.clotho.log.domain.OpLog.OpType;
import com.runmit.clotho.log.mapper.OpLogMapper;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月29日
 */
@Service
@Transactional(readOnly=true)
public class OpLogService{
	@Autowired
	private OpLogMapper mapper;
	
	@Transactional(readOnly=false)
	public void saveOpLog(String content,OpType opType,String opMod,String systemId,String createby){
		OpLog opLog = new OpLog(content,createby,opType,systemId,opMod);
		this.mapper.addOpLog(opLog);
	}
	
	@Transactional(readOnly=false)
	public void saveObj(Object obj,OpType opType,String opMod,String systemId,String createby){
		Gson gson = new Gson();
		String content = gson.toJson(obj);
		OpLog opLog = new OpLog(content,createby,opType,systemId,opMod);
		this.mapper.addOpLog(opLog);
	}
	
	@Transactional(readOnly=false)
	public void updateObj(Object objbefore,Object objafter,OpType opType,String opMod,String systemId,String createby){
		Gson gson = new Gson();
		String content = gson.toJson(objbefore)+"  <span style=\"color:red\">修改为</span>  "+gson.toJson(objafter);
		OpLog opLog = new OpLog(content,createby,opType,systemId,opMod);
		this.mapper.addOpLog(opLog);
	}
	
	public List<OpLog> getOpLogs(int start,int limit,OpType opType,String opMod,String systemId){
		return this.mapper.getOpLogs(start, limit, opType, opMod,systemId);
	}

}
