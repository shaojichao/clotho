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
public class RedirectTaskController {
	private static final Logger LOGGER = LoggerFactory
            .getLogger(RedirectTaskController.class);
	
	@RequestMapping(value = "/cdn/allTasks.do")
	public String toUserfeedback() {
		LOGGER.info("listTasks");
		return "cdn/allTask";
	}

}
