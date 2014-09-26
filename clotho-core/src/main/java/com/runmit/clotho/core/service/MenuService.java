package com.runmit.clotho.core.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runmit.clotho.core.domain.Menu;
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
    public void addMenu(Menu menu) {
    	menuMapper.addMenu(menu);
    }
    
    @Transactional(readOnly = true)
    public List<Menu> getMenuList(int parentID){
    	return menuMapper.getList(parentID);
    }
}
