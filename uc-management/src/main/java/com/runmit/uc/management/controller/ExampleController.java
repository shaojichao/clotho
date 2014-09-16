package com.runmit.uc.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author TianLiang
 */
@Controller
@RequestMapping(value = "/example")
public class ExampleController {

    @RequestMapping(value = "/modal")
    public String modalExample() {
        return "/example/modal";
    }

    @RequestMapping(value = "/modal_ext")
    public String modalExtExample() {
        return "/example/modal_ext";
    }

    @RequestMapping(value = "/table")
    public String tableExample() {
        return "/example/table";
    }

    @RequestMapping(value = "/form")
    public String formExample() {
        return "/example/form";
    }
}
