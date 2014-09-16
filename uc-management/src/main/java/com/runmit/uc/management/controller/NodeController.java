package com.runmit.uc.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author TianLiang
 */
@Controller
@RequestMapping(value = "/node")
public class NodeController {

    @RequestMapping(value = "")
    public String nodeIndex() {
        return "/node/index";
    }
    
     @RequestMapping(value = "/detail")
    public String nodeDetailIndex() {
        return "/node/detail/index";
    }
}
