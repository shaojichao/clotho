package com.runmit.clotho.core.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runmit.clotho.core.domain.admin.Menu;
import com.runmit.clotho.core.mapper.MenuMapper;
import com.runmit.clotho.log.domain.OpLog.OpType;
import com.runmit.clotho.log.service.OpLogService;

/**
 *
 * @author Scott.Xie
 */
@Service
@Transactional
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
	private OpLogService opLogService;
    
    @Transactional(readOnly = false)
    public void saveMenu(Menu menu) {
    	if(menu.getId()==null||menu.getId()==0){
    		menuMapper.addMenu(menu);
    		opLogService.saveObj(menu, OpType.INSERT, "menu", "clotho", menu.getCreatedBy());
    	}else{
    		Menu temp = this.menuMapper.getMenu(menu.getId());
    		menuMapper.updateMenu(menu);
    		opLogService.updateObj(temp,menu, OpType.UPDATE, "menu", "clotho", menu.getUpdateby());
    	}
    	
    }
    
    @Transactional(readOnly = true)
    public List<Menu> getMenuList(int parentID){
    	return menuMapper.getList(parentID);
    }
    
    @Transactional(readOnly = true)
    public List<Menu> getMenuListByAdminid(int adminid,int parentID){
    	return menuMapper.getListByAdminid(adminid,parentID);
    }
    
    @Transactional(readOnly = true)
    public List<Menu> getMenuListByRole(int parentID,int roleid){
    	return menuMapper.getListByRole(parentID,roleid);
    }
    
    @Transactional(readOnly = true)
    public List<Menu> getAllMenu(){
    	return menuMapper.getAllMenu();
    }
    
    @Transactional(readOnly = false)
    public void delMenu(int id,String adminName){
    	opLogService.saveObj(menuMapper.getMenu(id), OpType.DELETE, "menu", "clotho", adminName);
    	this.menuMapper.delMenu(id);
    }
}
