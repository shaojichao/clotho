package com.runmit.clotho.core.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runmit.clotho.core.domain.client.Client;
import com.runmit.clotho.core.mapper.ClientMapper;

/**
 *
 * @author zhipeng.tian
 */
@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientMapper clientMapper;
    
    @Transactional(readOnly = false)
    public void saveClient(Client client) {
    	if(client.getClientId()==null||client.getClientId()==0){
    		clientMapper.addClient(client);
    	}else{
    		clientMapper.updateClient(client);
    	}
    	
    }
    
    @Transactional(readOnly = true)
    public List<Client> getList(){
    	return clientMapper.getList();
    }
}
