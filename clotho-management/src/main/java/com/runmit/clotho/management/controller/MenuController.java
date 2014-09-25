package com.runmit.clotho.management.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runmit.clotho.core.domain.CurrentStatus;
import com.runmit.clotho.core.domain.Menu;

/**
 * @author zhipeng.tian
 *
 *2014年9月24日
 * 
 */
@Controller
@Component
@RequestMapping(value = "/menu")
public class MenuController {
	private static final Logger log = LoggerFactory
            .getLogger(RedirectController.class);
	
	@RequestMapping(value = "/list.do")
	public @ResponseBody List<Menu> getMenus(@RequestParam("adminID") int adminid) {
		
		Menu menu0 = new Menu();
		menu0.setId(3);
		menu0.setLeaf(true);
		menu0.setText("测试1");
		menu0.setParentID(0);
		menu0.setStatus(CurrentStatus.ACTIVE);
		menu0.setUrl("list-demo.do");
		
		List<Menu> list0 = new ArrayList<Menu>();
		list0.add(menu0);
		
		List<Menu> list = new ArrayList<Menu>();
		Menu menu = new Menu();
		menu.setId(1);
		menu.setLeaf(false);
		menu.setText("测试3");
		menu.setParentID(0);
		menu.setStatus(CurrentStatus.ACTIVE);
		menu.setChildren(list0);
		list.add(menu);
		
		Menu menu1 = new Menu();
		menu1.setId(2);
		menu1.setLeaf(true);
		menu1.setText("测试2");
		menu1.setParentID(0);
		menu1.setStatus(CurrentStatus.ACTIVE);
		menu1.setUrl("http://cn.bing.com");
		list.add(menu1);
		
		log.info("login to index.do");
		return list;
	}
}
