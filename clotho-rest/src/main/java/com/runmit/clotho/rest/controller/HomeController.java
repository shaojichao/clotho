package com.runmit.uc.rest.controller;

import com.knappsack.swagger4springweb.controller.ApiDocumentationController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This is an example of how you might extend the ApiDocumentationController in
 * order to set your own RequestMapping (instead of the default "/api") among
 * other possibilities. Going this route, you do not necessarily have to define
 * the controller in your servlet context.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController extends ApiDocumentationController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String documentation() {
        
        System.out.println("===========");
        return "home";
    }
}
