package com.runmit.clotho.management.controller.clotho;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runmit.clotho.core.domain.client.App;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.AppService;
import com.runmit.clotho.management.security.SessionUtil;

/**
 * @author zhipeng.tian
 *
 *2014年9月24日
 * 
 */
@Controller
@Component
@RequestMapping(value = "/clotho/app")
public class AppController {
	private static final Logger LOGGER = LoggerFactory
            .getLogger(AppController.class);
	
	@Autowired
    private AppService appService;
	
	@RequestMapping(value = "/allList.do")
	public @ResponseBody ExtEntity<App> getApps() {
		
		List<App> list = this.appService.getList();
		ExtEntity<App> entity = new ExtEntity<App>();
		entity.setResult(list.size());
		entity.setRows(list);
		return entity;
	}
	
	@RequestMapping(value = "/saveApp.do")
	public @ResponseBody ExtStatusEntity saveApp(App app,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		
		if(app.getAppId()==null||app.getAppId()==0){
			app.setCreateby(SessionUtil.getLoginAdminName(request));
		}else{
			app.setUpdateby(SessionUtil.getLoginAdminName(request));
		}
		try{
			this.appService.saveApp(app);
			
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("saveApp error",ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}
		
		LOGGER.info("saveApp");
		return entity;
	}
}
