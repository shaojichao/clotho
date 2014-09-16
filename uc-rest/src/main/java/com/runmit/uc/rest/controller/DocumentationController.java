package com.runmit.uc.rest.controller;

import com.knappsack.swagger4springweb.controller.ApiDocumentationController;
import com.wordnik.swagger.model.ApiInfo;
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
@RequestMapping(value = "/documentation")
public class DocumentationController extends ApiDocumentationController {

    public DocumentationController() {
        setBaseControllerPackage("com.runmit.uc.rest.controller");
//        List<String> controllerPackages = new ArrayList<String>();
//        controllerPackages.add("com.knappsack.swagger4springweb.controllers.additionalApi");
//        setAdditionalControllerPackages(controllerPackages);

        setBaseModelPackage("com.runmit.uc.rest.domain");
//        List<String> modelPackages = new ArrayList<String>();
//        modelPackages.add("com.knappsack.swagger4springweb.additionalModels");
//        setAdditionalModelPackages(modelPackages);
        setApiVersion("v1");

        ApiInfo apiInfo = new ApiInfo("用户中心接口文档及测试页面", "用户中心涉及所有接口的相关文档和测试均可在本页面进行",
                "http://localhost:8080/terms", "http://localhost:8080/contact", "", "");
        setApiInfo(apiInfo);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String documentation() {
        return "documentation";
    }
}
