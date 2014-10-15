package com.runmit.clotho.management.controller.cdn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiangchao.zhou
 *
 *2014年9月24日
 * 
 */
@Controller
@Component
public class RedirectServerController {
	private static final Logger LOGGER = LoggerFactory
            .getLogger(RedirectServerController.class);
	
	@RequestMapping(value = "/cdn/allServers.do")
	public String toUserfeedback() {
		LOGGER.info("listServer");
		return "cdn/allServer";
	}

}
