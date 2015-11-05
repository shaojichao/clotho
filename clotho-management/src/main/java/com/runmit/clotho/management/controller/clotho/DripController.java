package com.runmit.clotho.management.controller.clotho;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runmit.clotho.core.domain.drip.DripRecord;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.service.DripService;

/**
 * @author zhipeng.tian
 * 
 * @date 2015年11月5日
 */
@Controller
@RequestMapping(value = "/clotho/drip")
public class DripController {
private static final Logger LOGGER = LoggerFactory.getLogger(DripController.class);
	
	@Autowired
	private DripService dripService;
	
	@RequestMapping(value = "/allList.do")
	public  @ResponseBody ExtEntity<DripRecord> getDripRecords(
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
            @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
            HttpServletRequest request) {
		List<DripRecord> list = this.dripService.getList(start, limit);
		ExtEntity<DripRecord> entity = new ExtEntity<DripRecord>();
		entity.setResult(dripService.getCount());
		entity.setRows(list);
		return entity;
	}
}
