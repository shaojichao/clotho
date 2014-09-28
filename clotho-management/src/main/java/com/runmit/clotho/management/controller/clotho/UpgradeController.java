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

import com.runmit.clotho.core.domain.upgrade.Version;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.service.VersionService;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月26日
 */
@Controller
@Component
@RequestMapping(value = "/clotho/upgrade")
public class UpgradeController {
	private static final Logger log = LoggerFactory
            .getLogger(UpgradeController.class);
	
	@Autowired
    private VersionService versionService;
	
	@RequestMapping(value = "/list.do")
	public @ResponseBody ExtEntity<Version> getVersions(
			@RequestParam(value = "start", required = false,defaultValue="0") Integer start,
            @RequestParam(value = "limit", required = false,defaultValue="20") Integer limit) {
		
		
		
		ExtEntity<Version> listdata = new ExtEntity<Version>();
		
		List<Version> list = versionService.getList(start, limit);
		
		listdata.setRows(list);
		listdata.setResult(versionService.getCount());
		
		log.info("getVersions");
		return listdata;
	}
}
