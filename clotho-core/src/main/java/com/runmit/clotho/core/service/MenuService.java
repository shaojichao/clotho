package com.runmit.clotho.core.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runmit.clotho.core.domain.admin.Menu;
import com.runmit.clotho.core.mapper.MenuMapper;

/**
 *
 * @author Scott.Xie
 */
@Service
@Transactional
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;
    
    @Transactional(readOnly = false)
    public void saveMenu(Menu menu) {
    	if(menu.getId()==null||menu.getId()==0){
    		menuMapper.addMenu(menu);
    	}else{
    		menuMapper.updateMenu(menu);
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
    public void delMenu(int id){
    	this.menuMapper.delMenu(id);
    }
}
