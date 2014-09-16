package com.runmit.clotho.rest.controller;

import com.runmit.clotho.core.service.UpgradeService;
import com.runmit.clotho.rest.domain.Upgrade;
import com.runmit.clotho.rest.domain.UpgradeResp;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author XP
 */
@RestController
@RequestMapping(value = "/v1/upgrade")
public class UpgradeController {

    private static final Logger log = LoggerFactory
            .getLogger(UpgradeController.class);

    @Autowired
    private UpgradeService upgradeService;

    /**
     * 获取当前升级版本
     * 参数说明在各自的RequestBody类中
     */
    @RequestMapping(value = "/getupgrade", method = RequestMethod.POST, consumes = {"application/json"})
    @ApiOperation(value = "获取升级版本接口", notes = "用于客户端获取软件的可升级最新版本", httpMethod = "POST", consumes = "application/json", produces = "application/json", protocols = "http, https", nickname = "", response = Upgrade.class)
    @ApiResponses(value = {
            @ApiResponse(code=0,message = "成功")
    })
    public ResponseEntity<UpgradeResp> verifyMobile(@RequestBody @Valid Upgrade upgrade, BindingResult bindingResult) {
        UpgradeResp ur=new UpgradeResp();
        if (bindingResult.hasErrors()) {
//            FIXME:添加log输出错误类型
//            response.setRtn(bindingResult.getAllErrors().get(0).getDefaultMessage());
//            cr.setRtn(RestConst.rtn_BadRequest);
            return new ResponseEntity<>(ur, HttpStatus.BAD_REQUEST);
        }
//        upgradeService.getdependencebyorigin(upgrade.getVersion())
//        cr.setRtn(RestConst.rtn_OK);
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }

}
