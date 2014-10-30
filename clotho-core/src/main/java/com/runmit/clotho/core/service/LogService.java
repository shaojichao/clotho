package com.runmit.clotho.core.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.runmit.clotho.log.domain.OpLog;
import com.runmit.clotho.log.domain.OpLog.OpType;
import com.runmit.clotho.log.service.OpLogService;

/**
 * @author zhipeng.tian
 * 
 * 2014年10月30日
 */
@Component
public class LogService {
	@Reference(version="1.0.0")
	private OpLogService opLogService;
	
	public List<OpLog> getOpLogs(int start,int limit,OpType opType,String opMod,String systemId){
		return this.opLogService.getOpLogs(start, limit, opType, opMod, systemId);
	}
	
	public long getOpLogsCount(OpType opType,String opMod,String systemId){
		return this.opLogService.getOpLogsCount(opType, opMod, systemId);
	}
}
