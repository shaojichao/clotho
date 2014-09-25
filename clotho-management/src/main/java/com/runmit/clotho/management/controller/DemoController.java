package com.runmit.clotho.management.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runmit.clotho.core.domain.CurrentStatus;
import com.runmit.clotho.core.domain.Menu;
import com.runmit.clotho.core.dto.ExtEntity;

/**
 * 
 * @author zhipeng.tian
 *
 */
@Controller
@Component
@RequestMapping(value = "/demo")
public class DemoController {
	
	private static final Logger log = LoggerFactory
            .getLogger(DemoController.class);
	
	@RequestMapping(value = "/list.do")
	public @ResponseBody ExtEntity<Menu> getMenus() {
		
		ExtEntity<Menu> menus = new ExtEntity<Menu>();
		
		List<Menu> list = new ArrayList<Menu>();
		Menu menu = new Menu();
		menu.setId(1);
		menu.setLeaf(true);
		menu.setText("测试1");
		menu.setParentID(0);
		menu.setStatus(CurrentStatus.ACTIVE);
		menu.setUrl("list-demo.do");
		list.add(menu);
		
		Menu menu1 = new Menu();
		menu1.setId(2);
		menu1.setLeaf(true);
		menu1.setText("测试2");
		menu1.setParentID(0);
		menu1.setStatus(CurrentStatus.ACTIVE);
		menu1.setUrl("http://cn.bing.com");
		list.add(menu1);
		
		menus.setResult(2);
		menus.setRows(list);
		
		log.info("demo list");
		return menus;
	}
}
