package com.runmit.clotho.management.controller.cdn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhipeng.tian
 * 
 * 2014年10月17日
 */
@Controller
@Component
public class RedirectCDNController {
	private static final Logger LOGGER = LoggerFactory
            .getLogger(RedirectCDNController.class);
	
	@RequestMapping(value = "/cdn/dispatch.do")
	public String toDispatch() {
		LOGGER.info("List Server File");
		return "cdn/dispatch";
	}

	@RequestMapping(value = "/cdn/file.do")
	public String toFile() {
		LOGGER.info("List Server File");
		return "cdn/file";
	}
	
	@RequestMapping(value = "/cdn/allJobs.do")
	public String toAllJobs() {
		LOGGER.info("listJobs");
		return "cdn/allJob";
	}
	
	@RequestMapping(value = "/cdn/allNodes.do")
	public String toAllNode() {
		LOGGER.info("listNode");
		return "cdn/allNode";
	}
	
	@RequestMapping(value = "/cdn/allServers.do")
	public String toAllServer() {
		LOGGER.info("listServer");
		return "cdn/allServer";
	}
	
	@RequestMapping(value = "/cdn/serverfile.do")
	public String toServerFile() {
		LOGGER.info("List Server File");
		return "cdn/serverfile";
	}
	
	@RequestMapping(value = "/cdn/allTasks.do")
	public String toAllTask() {
		LOGGER.info("listTasks");
		return "cdn/allTask";
	}
}
