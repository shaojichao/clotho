package com.runmit.clotho.management.controller.clotho;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhipeng.tian
 *
 *2014年9月24日
 * 
 */
@Controller
@Component
public class RedirectClothoController {
	private static final Logger LOGGER = LoggerFactory
            .getLogger(RedirectClothoController.class);
	
	@RequestMapping(value = "/clotho/userfeedback.do")
	public String toUserfeedback() {
		LOGGER.info("toUserfeedback");
		return "clotho/userfeedback";
	}
	
	@RequestMapping(value = "/clotho/upgrade.do")
	public String toUpgrade() {
		LOGGER.info("toUpgrade");
		return "clotho/upgrade";
	}
	
	@RequestMapping(value = "/clotho/upgradeplan.do")
	public String toUpgradePlan() {
		LOGGER.info("toUpgradePlan");
		return "clotho/upgradeplan";
	}
	
	@RequestMapping(value = "/clotho/client.do")
	public String toClient() {
		LOGGER.info("toClient");
		return "clotho/client";
	}
	
	@RequestMapping(value = "/clotho/oplog.do")
	public String toOpLog() {
		LOGGER.info("toOpLog");
		return "clotho/oplog";
	}
}
