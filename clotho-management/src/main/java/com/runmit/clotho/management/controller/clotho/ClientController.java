package com.runmit.clotho.management.controller.clotho;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runmit.clotho.core.domain.admin.Admin;
import com.runmit.clotho.core.domain.client.Client;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.ClientService;
import com.runmit.clotho.management.security.SecurityConstant;

/**
 * @author zhipeng.tian
 *
 *2014年9月24日
 * 
 */
@Controller
@Component
@RequestMapping(value = "/clotho/client")
public class ClientController {
	private static final Logger LOGGER = LoggerFactory
            .getLogger(ClientController.class);
	
	@Autowired
    private ClientService clientService;
	
	@RequestMapping(value = "/allList.do")
	public @ResponseBody ExtEntity<Client> getClients() {
		
		List<Client> list = this.clientService.getList();
		ExtEntity<Client> entity = new ExtEntity<Client>();
		entity.setResult(list.size());
		entity.setRows(list);
		return entity;
	}
	
	@RequestMapping(value = "/saveClient.do")
	public @ResponseBody ExtStatusEntity saveClient(Client client,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute(SecurityConstant.ADMIN_SESSION_ATTRIBUTE);
		if(client.getClientId()==null||client.getClientId()==0){
			client.setCreateby(admin.getName());
		}else{
			client.setUpdateby(admin.getName());
		}
		try{
			this.clientService.saveClient(client);
			
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("saveVersion error",ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}
		
		LOGGER.info("saveClient");
		return entity;
	}
}
