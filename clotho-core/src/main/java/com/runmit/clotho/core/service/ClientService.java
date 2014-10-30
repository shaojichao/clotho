package com.runmit.clotho.core.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.runmit.clotho.core.domain.client.Client;
import com.runmit.clotho.core.mapper.ClientMapper;
import com.runmit.clotho.log.domain.OpLog.OpType;
import com.runmit.clotho.log.service.OpLogService;

/**
 *
 * @author zhipeng.tian
 */
@Service
@Transactional
@Component
public class ClientService {

    @Autowired
    private ClientMapper clientMapper;
//  @Autowired
    @Reference(version="1.0.0")
	private OpLogService opLogService;
  
    @Transactional(readOnly = false)
    public void saveClient(Client client) {
    	if(client.getClientId()==null||client.getClientId()==0){
    		clientMapper.addClient(client);
    		this.opLogService.saveObj(client, OpType.INSERT, "client", "clotho", client.getCreateby());
    	}else{
    		Client temp = this.clientMapper.getClient(client.getClientId());
    		clientMapper.updateClient(client);
    		this.opLogService.updateObj(temp, client, OpType.UPDATE, "client", "clotho", client.getUpdateby());
    	}
    	
    }
    
    @Transactional(readOnly = true)
    public List<Client> getList(){
    	return clientMapper.getList();
    }
}
