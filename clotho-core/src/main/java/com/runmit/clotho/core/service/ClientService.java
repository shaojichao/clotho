package com.runmit.clotho.core.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ClientService {

    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private OpLogService oplogService;
    
    @Transactional(readOnly = false)
    public void saveClient(Client client) {
    	if(client.getClientId()==null||client.getClientId()==0){
    		clientMapper.addClient(client);
    		this.oplogService.saveObj(client, OpType.INSERT, "client", "clotho", client.getCreateby());
    	}else{
    		Client temp = this.clientMapper.getClient(client.getClientId());
    		clientMapper.updateClient(client);
    		this.oplogService.updateObj(temp, client, OpType.UPDATE, "client", "clotho", client.getUpdateby());
    	}
    	
    }
    
    @Transactional(readOnly = true)
    public List<Client> getList(){
    	return clientMapper.getList();
    }
}
