package com.runmit.clotho.management.controller.metis;

import com.runmit.clotho.core.domain.metis.avro_schema;
import com.runmit.clotho.core.domain.upgrade.UpgradePlan;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xutao
 *
 *2014年10月9日
 * 
 */

@Controller

@RequestMapping(value = "/metis")
public class RedirectMetisController {

    @Autowired(required=false)
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;
    private static final Logger log = LoggerFactory
            .getLogger(RedirectMetisController.class);


//    @RequestMapping(value = "/upload.do")
//	public @ResponseBody ExtStatusEntity uploadSchema(@RequestParam("path") String path,HttpServletRequest request) {
//
//        ExtStatusEntity entity = new ExtStatusEntity();
//		log.info(path);
//        HttpHeaders headers =new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity req=new HttpEntity(path, headers);
//
//        String res=restTemplate.postForObject("http://192.168.20.161:8080/metis-schemaregister/schema/update",req,String.class);
//
//        entity.setMsg("succeed");
//        entity.setSuccess(true);
//
//
//		return entity;
//	}

    @RequestMapping(value = "/allschema.do" )
    public String indexSchema() {
        return "metis/schema";
    }

//    @RequestMapping(value = "/schema.do",method= RequestMethod.GET)
//    public @ResponseBody ExtEntity<avro_schema> findAllSchema() {
//        log.info("----------------");
//
//        ExtEntity<avro_schema> listdata = new ExtEntity<avro_schema>();
//        HttpHeaders headers =new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity request=new HttpEntity("", headers);
//        List<avro_schema> list=restTemplate.postForObject("http://192.168.20.161:8080/metis-schemaregister/schema/list", request, List.class);
//        listdata.setRows(list);
//        listdata.setResult(list.size());
//        return listdata;
//    }
    
}
