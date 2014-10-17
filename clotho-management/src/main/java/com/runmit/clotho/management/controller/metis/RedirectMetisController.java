package com.runmit.clotho.management.controller.metis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

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
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RedirectMetisController.class);


    @RequestMapping(value = "/allschema.do" )
    public String indexSchema() {
    	LOGGER.info("allschema");
        return "metis/schema";
    }

}
