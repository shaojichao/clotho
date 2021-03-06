package com.runmit.clotho.management.controller.clotho;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.service.LogService;
import com.runmit.clotho.log.domain.OpLog;
import com.runmit.clotho.log.domain.OpLog.OpType;

/**
 * @author zhipeng.tian
 *
 *2014年10月20日
 * 
 */
@Controller
@Component
@RequestMapping(value = "/clotho/oplog")
public class OpLogController {
	private static final Logger LOGGER = LoggerFactory
            .getLogger(OpLogController.class);
	
	@Autowired
	private LogService logService;
  
	@RequestMapping(value = "/list.do")
	public @ResponseBody ExtEntity<OpLog> getlist(@RequestParam(value="start",defaultValue="0")int start,
			@RequestParam(value="limit",defaultValue="20")int limit,
			@RequestParam(value="opType",required=false)OpType opType,
			@RequestParam(value="opMod",required=false)String opMod,
			@RequestParam(value="systemId",defaultValue="ALL")String systemId) {
		List<OpLog> list = this.logService.getOpLogs(start, limit, opType, opMod,systemId);
		ExtEntity<OpLog> entity = new ExtEntity<OpLog>();
		entity.setResult(this.logService.getOpLogsCount(opType, opMod, systemId));
		entity.setRows(list);
		LOGGER.info("getLogs");
		return entity;
	}
}
