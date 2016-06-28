package com.runmit.clotho.rest.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.runmit.clotho.core.domain.client.Client;
import com.runmit.clotho.core.service.ClientService;
import com.runmit.clotho.rest.domain.ClientResp;
import com.runmit.clotho.rest.domain.CommonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


/**
 * Created by Qian.Liu on 2016/6/28.
 */

@RestController
@RequestMapping(value = "/clientId")
public class ClientIdController {

    private static final Logger LOGGER =  LoggerFactory.getLogger(ClientIdController.class);

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/getAllClientIds")
    public ClientResp getWeatherForecast() {
        List<Client> clientList = clientService.getList();
        ClientResp clientResp = new ClientResp();
        if (null != clientList) {
            clientResp.setRtn(0);
            JSONArray jsonArray = new JSONArray();
            for ( int i=0;i<clientList.size();i++ ){
                JSONObject jo = new JSONObject();
                jo.put("clientId", clientList.get(i).getClientId());
                jo.put("superProjectId", clientList.get(i).getSuperProjectId());
                jo.put("name", clientList.get(i).getName());
                jsonArray.add(jo);
            }
            System.out.println(jsonArray.toJSONString());
            clientResp.setClientIdLists(jsonArray.toJSONString());

        }

        return clientResp;
    }

}
