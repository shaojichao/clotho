package com.runmit.clotho.management.controller.uc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * @author Scott.Xie
 *         <p/>
 *         2014年10月24日
 */

@Controller
@RequestMapping(value = "/uc")
public class UserController {

    @Autowired(required = false)
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/deviceinfo.do")
    public String indexDeviceInfo() {
        LOGGER.info("page:deviceinfo");
        return "uc/deviceinfo";
    }

    @RequestMapping(value = "/userinfo.do")
    public String indexUserInfo() {
        LOGGER.info("page:userinfo");
        return "uc/userinfo";
    }
}
