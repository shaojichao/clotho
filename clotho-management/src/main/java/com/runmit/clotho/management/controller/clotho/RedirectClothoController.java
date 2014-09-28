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
	private static final Logger log = LoggerFactory
            .getLogger(RedirectClothoController.class);
	
	@RequestMapping(value = "/clotho/userfeedback.do")
	public String toUserfeedback() {
		log.info("toUserfeedback");
		return "clotho/userfeedback";
	}
	
	@RequestMapping(value = "/clotho/upgrade.do")
	public String toUpgrade() {
		log.info("toUpgrade");
		return "clotho/upgrade";
	}
	
	@RequestMapping(value = "/clotho/upgradeplan.do")
	public String toUpgradePlan() {
		log.info("toUpgradePlan");
		return "clotho/upgradeplan";
	}
}
