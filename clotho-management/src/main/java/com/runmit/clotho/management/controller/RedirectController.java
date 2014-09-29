package com.runmit.clotho.management.controller;

import javax.servlet.http.HttpServletRequest;

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
public class RedirectController {
	private static final Logger log = LoggerFactory
            .getLogger(RedirectController.class);
	
	@RequestMapping(value = "/index.do")
	public String toIndex(HttpServletRequest request) {
		log.info("login to index.do");
		return "index";
	}
	@RequestMapping(value = "/admin/menu.do")
	public String toMenu(HttpServletRequest request) {
		return "menu";		
	}
}
