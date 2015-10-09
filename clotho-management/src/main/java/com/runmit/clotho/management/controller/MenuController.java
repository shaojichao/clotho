package com.runmit.clotho.management.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runmit.clotho.core.domain.admin.Menu;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.MenuService;
import com.runmit.clotho.management.domain.MenuDTO;
import com.runmit.clotho.management.security.SessionUtil;

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
	private static final Logger LOGGER = LoggerFactory
            .getLogger(MenuController.class);
	
	@Autowired
    private MenuService menuService;
	
	/**
	 * menu list
	 * @param adminid
	 * @return
	 */
	@RequestMapping(value = "/list.do")
	public @ResponseBody List<Menu> getMenus(@RequestParam(value="adminID",defaultValue="0") int adminid) {
		
		List<Menu> list = this.menuService.getMenuListByAdminid(adminid,0);
		
		//二级联动
		/*for(Menu menu:list){
			if(!menu.getLeaf()){
				menu.setChildren(this.menuService.getMenuListByAdminid(adminid,menu.getId()));
			}
		}*/
		setMenuChildren(list,adminid);
		
		LOGGER.info("getMenus");
		return list;
	}
	
	private void setMenuChildren(List<Menu> list,int adminid){
		for(Menu menu:list){
			if(!menu.getLeaf()){
				menu.setChildren(this.menuService.getMenuListByAdminid(adminid,menu.getId()));
				setMenuChildren(menu.getChildren(),adminid);
			}
		}
	}
	
	@RequestMapping(value = "/getMenuByRole.do")	
	public @ResponseBody List<MenuDTO> getMenuByRole(@RequestParam(value="roleid",defaultValue="0") int roleid) {
		if(roleid==0){
			return null;
		}
		List<Menu> list = this.menuService.getMenuListByRole(0,roleid);
		List<MenuDTO> dtos = new ArrayList<MenuDTO>();
		//二级联动
		/*for(Menu menu:list){
			MenuDTO dto = new MenuDTO();
			if(menu.getRoleid()!=null){
				dto.setChecked(true);
			}
			dto.setExpanded(true);
			dto.setId(menu.getId());
			dto.setText(menu.getText());
			dto.setLeaf(menu.getLeaf());
			if(!menu.getLeaf()){
				List<Menu> children = this.menuService.getMenuListByRole(menu.getId(),roleid);
				List<MenuDTO> dtosc = new ArrayList<MenuDTO>();
				for(Menu child:children){
					MenuDTO chdto = new MenuDTO();
					if(child.getRoleid()!=null){
						chdto.setChecked(true);
					}
					chdto.setExpanded(false);
					chdto.setId(child.getId());
					chdto.setText(child.getText());
					chdto.setLeaf(child.getLeaf());
					dtosc.add(chdto);
				}
				dto.setChildren(dtosc);
			}
			dtos.add(dto);
		}*/
		setMenuDTOChildren(list,dtos,roleid);
		LOGGER.info("getMenuDTOs");
		return dtos;
	}
	
	private void setMenuDTOChildren(List<Menu> list,List<MenuDTO> dtos,int roleid){
		for(Menu menu:list){
			MenuDTO dto = new MenuDTO();
			if(menu.getRoleid()!=null){
				dto.setChecked(true);
			}
			dto.setExpanded(true);
			dto.setId(menu.getId());
			dto.setText(menu.getText());
			dto.setLeaf(menu.getLeaf());
			if(!menu.getLeaf()){
				List<Menu> children = this.menuService.getMenuListByRole(menu.getId(),roleid);
				List<MenuDTO> dtosc = new ArrayList<MenuDTO>();
				setMenuDTOChildren(children,dtosc,roleid);
//				for(Menu child:children){
//					MenuDTO chdto = new MenuDTO();
//					if(child.getRoleid()!=null){
//						chdto.setChecked(true);
//					}
//					chdto.setExpanded(false);
//					chdto.setId(child.getId());
//					chdto.setText(child.getText());
//					chdto.setLeaf(child.getLeaf());
//					dtosc.add(chdto);
//				}
				dto.setChildren(dtosc);
			}
			dtos.add(dto);
		}
	}
	
	@RequestMapping(value = "/rootlist.do")
	public @ResponseBody ExtEntity<Menu> getMenus() {
		List<Menu> list = this.menuService.getMenuList(0);
		Menu e = new Menu();
		e.setId(0);
		e.setText("无");
		list.add(0, e);
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
		
		if(menu.getId()==null||menu.getId()==0){
			menu.setCreatedBy(SessionUtil.getLoginAdminName(request));
		}else{
			menu.setUpdateby(SessionUtil.getLoginAdminName(request));
		}
		try{
			this.menuService.saveMenu(menu);
			
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("saveVersion error",ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}
		
		LOGGER.info("saveMenu");
		return entity;
	}
	
	@RequestMapping(value = "/delMenu.do")
	public @ResponseBody ExtStatusEntity delMenu(@RequestParam("id")int id,HttpServletRequest request){
		ExtStatusEntity entity = new ExtStatusEntity();
		try{
			this.menuService.delMenu(id,SessionUtil.getLoginAdminName(request));
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("saveVersion error",ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}
		
		LOGGER.info("delMenu");
		return entity;
		
	}
}
