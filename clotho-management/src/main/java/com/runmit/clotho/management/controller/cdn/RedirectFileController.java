package com.runmit.clotho.management.controller.cdn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mengheng.li@runmit.com 2014/10/14 10:31
 */
@Controller
@Component
public class RedirectFileController {
	private static final Logger LOGGER = LoggerFactory
            .getLogger(RedirectFileController.class);
	
	@RequestMapping(value = "/cdn/file.do")
	public String toServerFile() {
		LOGGER.info("List Server File");
		return "cdn/file";
	}

}
