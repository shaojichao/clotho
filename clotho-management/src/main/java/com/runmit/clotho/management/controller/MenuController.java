package com.runmit.clotho.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runmit.clotho.core.domain.admin.Menu;
import com.runmit.clotho.core.service.MenuService;

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
	
	@Autowired
    private MenuService menuService;
	
	@RequestMapping(value = "/list.do")
	public @ResponseBody List<Menu> getMenus(@RequestParam(value="adminID",defaultValue="0") int adminid) {
		
		List<Menu> list = this.menuService.getMenuList(0);
		
		//二级联动
		for(Menu menu:list){
			if(!menu.getLeaf()){
				menu.setChildren(this.menuService.getMenuList(menu.getId()));
			}
		}
		
		log.info("getMenus");
		return list;
	}
	
}
