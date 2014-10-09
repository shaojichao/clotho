package com.runmit.clotho.management.controller.metis;

import com.runmit.clotho.core.domain.metis.avro_schema;
import com.runmit.clotho.core.domain.upgrade.UpgradePlan;
import com.runmit.clotho.core.dto.ExtEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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
    @Autowired
    private RestTemplate template;
    private static final Logger log = LoggerFactory
            .getLogger(RedirectMetisController.class);
	

    @RequestMapping(value = "/upload.do",method= RequestMethod.POST)
	public String uploadSchema(@RequestBody String path) {
		log.info(path);
        HttpHeaders headers =new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request=new HttpEntity(path, headers);
        String res=template.postForObject("url",request,String.class);



		return "metis/schema";
	}

    @RequestMapping(value = "/allschema.do" )
    public String indexSchema() {
        return "metis/schema";
    }

    @RequestMapping(value = "/schema.do",method= RequestMethod.POST)
    public @ResponseBody ExtEntity<avro_schema> findAllSchema() {
        ExtEntity<avro_schema> listdata = new ExtEntity<avro_schema>();
        HttpHeaders headers =new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request=new HttpEntity("http://clotho.d5dstore.com:8080/metis-schemaregister/schema/list", headers);
        List<avro_schema> list=template.postForObject("url",request,List.class);
        listdata.setRows(list);
        listdata.setResult(list.size());
        return listdata;
    }
    
}
