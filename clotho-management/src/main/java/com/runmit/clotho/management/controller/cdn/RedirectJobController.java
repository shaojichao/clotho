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
public class RedirectJobController {
	private static final Logger LOGGER = LoggerFactory
            .getLogger(RedirectJobController.class);
	
	@RequestMapping(value = "/cdn/allJobs.do")
	public String toUserfeedback() {
		LOGGER.info("listJobs");
		return "cdn/allJob";
	}

}
