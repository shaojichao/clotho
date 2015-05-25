package com.runmit.clotho.core.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.runmit.clotho.core.domain.client.App;
import com.runmit.clotho.core.mapper.AppMapper;
import com.runmit.clotho.log.domain.OpLog.OpType;
import com.runmit.clotho.log.service.OpLogService;

/**
 *
 * @author zhipeng.tian
 */
@Service
@Transactional
@Component
public class AppService {

    @Autowired
    private AppMapper appMapper;
//  @Autowired
    @Reference(version="1.0.0")
	private OpLogService opLogService;
  
    @Transactional(readOnly = false)
    public void saveApp(App app) {
    	if(app.getAppId()==null||app.getAppId()==0){
    		appMapper.addApp(app);
    		this.opLogService.saveObj(app, OpType.INSERT, "app", "clotho", app.getCreateby());
    	}else{
    		App temp = this.appMapper.getApp(app.getAppId());
    		appMapper.updateApp(app);
    		this.opLogService.updateObj(temp, app, OpType.UPDATE, "app", "clotho", app.getUpdateby());
    	}
    	
    }
    
    @Transactional(readOnly = true)
    public List<App> getList(){
    	return appMapper.getList();
    }
}
