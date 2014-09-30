package com.runmit.clotho.management.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runmit.clotho.core.domain.admin.Admin;
import com.runmit.clotho.core.domain.admin.Menu;
import com.runmit.clotho.core.domain.upgrade.Version;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.MenuService;
import com.runmit.clotho.management.security.SecurityConstant;

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
	
	/**
	 * menu list
	 * @param adminid
	 * @return
	 */
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
	
	@RequestMapping(value = "/rootlist.do")
	public @ResponseBody ExtEntity<Menu> getMenus() {
		List<Menu> list = this.menuService.getMenuList(0);
		Menu e = new Menu();
		e.setId(0);
		e.setText("无");
		list.add(e);
		ExtEntity<Menu> entity = new ExtEntity<Menu>();
		entity.setResult(list.size());
		entity.setRows(list);
		return entity;
	}
	
	@RequestMapping(value = "/allList.do")
	public @ResponseBody ExtEntity<Menu> getallMenus() {
		
		List<Menu> list = this.menuService.getAllMenu();
		ExtEntity<Menu> entity = new ExtEntity<Menu>();
		entity.setResult(list.size());
		entity.setRows(list);
		return entity;
	}
	
	@RequestMapping(value = "/saveMenu.do")
	public @ResponseBody ExtStatusEntity saveMenu(Menu menu,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute(SecurityConstant.ADMIN_SESSION_ATTRIBUTE);
		if(menu.getId()==null||menu.getId()==0){
			menu.setCreatedBy(admin.getName());
		}
		try{
			this.menuService.saveMenu(menu);
			
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			log.error("saveVersion error",ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}
		
		log.info("saveMenu");
		return entity;
	}
	
	@RequestMapping(value = "/delMenu.do")
	public @ResponseBody ExtStatusEntity delMenu(@RequestParam("id")int id){
		ExtStatusEntity entity = new ExtStatusEntity();
		try{
			this.menuService.delMenu(id);
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			log.error("saveVersion error",ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}
		
		log.info("delMenu");
		return entity;
		
	}
}
