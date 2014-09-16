package com.runmit.clotho.rest.controller;

/**
 * Created by gg on 2014/8/26.
 */
import com.knappsack.swagger4springweb.controller.ApiDocumentationController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author XP
 */
@Controller
@RequestMapping(value = "/")
public class ViewController extends ApiDocumentationController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String documentation() {
        return "home";
    }
}
