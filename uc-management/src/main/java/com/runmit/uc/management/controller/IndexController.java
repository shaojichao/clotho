package com.runmit.uc.management.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="mailto:wang-shuai@letv.com">Ousui</a>
 * @date 2012-5-23
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("")
    public String frame(HttpServletRequest request) {
        return "frame";
    }

}
