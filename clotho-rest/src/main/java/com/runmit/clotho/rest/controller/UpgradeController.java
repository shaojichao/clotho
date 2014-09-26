package com.runmit.clotho.rest.controller;

import com.runmit.clotho.core.domain.upgrade.UpgradePlan;
import com.runmit.clotho.core.domain.upgrade.Version;
import com.runmit.clotho.core.service.VersionService;
import com.runmit.clotho.rest.common.RestConst;
import com.runmit.clotho.rest.domain.Upgrade;
import com.runmit.clotho.rest.domain.UpgradeResp;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XP
 */
@RestController
@RequestMapping(value = "/upgrade")
public class UpgradeController {

    private static final Logger log = LoggerFactory
            .getLogger(UpgradeController.class);

    @Autowired
    private VersionService versionService;

    /**
     * 获取当前升级版本
     * 参数说明在各自的RequestBody类中
     */
    @RequestMapping(value = "/getupgrade", method = RequestMethod.GET)
    @ApiOperation(value = "获取升级版本接口", notes = "用于客户端获取软件的可升级最新版本", httpMethod = "POST", consumes = "application/json", produces = "application/json", protocols = "http, https", nickname = "", response = Upgrade.class)
    @ApiResponses(value = {
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=-1,message = "当前版本无需升级"),
            @ApiResponse(code=1,message = "传入版本不存在"),
            @ApiResponse(code=2,message = "clientid无对应最新版本信息"),
            @ApiResponse(code=3,message = "关联版本无法找到基础版本信息")
    })
    public ResponseEntity<UpgradeResp> getupgrade(@RequestParam("version") String version,
                                                  @RequestParam("clientid") String clientidget) {
        Upgrade upgrade=new Upgrade();
        upgrade.setClientid(clientidget);
        upgrade.setVersion(version);
        UpgradeResp ur=new UpgradeResp();
//        if (bindingResult.hasErrors()) {
//            log.error("getupgrade request error,request error code:" + bindingResult.getAllErrors().get(0).getDefaultMessage());
//            return new ResponseEntity<>(ur, HttpStatus.BAD_REQUEST);
//        }
        int clientid;
        try {
            clientid = Integer.parseInt(upgrade.getClientid());
            if(clientid<=0){
                log.error("getupgrade request error,clientid value error:"+clientid);
                return new ResponseEntity<>(ur, HttpStatus.BAD_REQUEST);
            }
        }catch (NumberFormatException e){
            log.error("getupgrade request error,clientid is not int"+e);
            return new ResponseEntity<>(ur, HttpStatus.BAD_REQUEST);
        }
        Version currentversion=versionService.getbyversion(upgrade.getVersion());
        if(currentversion==null){
            log.error("getupgrade request error,current version is not exists:"+upgrade.getVersion());
            ur.setRtn(RestConst.RTN_GETUPGRADE_CURRENTVERSION_NOTEXIST);
            return new ResponseEntity<>(ur, HttpStatus.OK);
        }
        UpgradePlan upgradePlan=versionService.getUpgradePlanbyversion(upgrade.getVersion());
        if(upgradePlan==null){
//            无对应关联升级版本,查找clientid对应的最新版本
            Version lastestversion=versionService.getLastestbyclientid(clientid);
            if(lastestversion==null){
                log.error("getupgrade request error,clientid get lastestversion is not exists:"+upgrade.getVersion());
                ur.setRtn(RestConst.RTN_GETUPGRADE_CLIENTIDGETVERSION_NOTEXIST);
                return new ResponseEntity<>(ur, HttpStatus.OK);
            }
            if(lastestversion.getSerialno().compareToIgnoreCase(currentversion.getSerialno())>0){
                ur.setRtn(RestConst.RTN_OK);
                ur.setIntroduction(lastestversion.getMemo());
                ur.setNew_version(lastestversion.getVersion());
                ur.setShow_type(String.valueOf(lastestversion.getShowtype()));
                ur.setUpgrade_type(String.valueOf(lastestversion.getUpgradetype()));
                ur.setUpgrade_url(lastestversion.getPkgurl());
                return new ResponseEntity<>(ur, HttpStatus.OK);
            }else{
                log.debug("getupgrade request error,current version is lastest:" + upgrade.getVersion());
                ur.setRtn(RestConst.RTN_GETUPGRADE_VERSION_LASTEST);
            }
            return new ResponseEntity<>(ur, HttpStatus.OK);
        }else{
            Version lastestversion=versionService.getbyserialno(upgradePlan.getUpgradeid());
            if(lastestversion==null){
                log.error("getupgrade request error,upgradeplan get version is not exists:"+upgrade.getVersion());
                ur.setRtn(RestConst.RTN_GETUPGRADE_PLANGETVERSION_NOTEXIST);
                return new ResponseEntity<>(ur, HttpStatus.OK);
            }
//            版本升级变更中信息为空,则到基础版本中找版本描述信息
            if(StringUtils.isBlank(upgradePlan.getMemo())){
                ur.setIntroduction(lastestversion.getMemo());
            }else{
                ur.setIntroduction(upgradePlan.getMemo());
            }
            ur.setRtn(RestConst.RTN_OK);
            ur.setShow_type(String.valueOf(upgradePlan.getShowtype()));
            ur.setUpgrade_type(String.valueOf(upgradePlan.getUpgradetype()));
            ur.setNew_version(lastestversion.getVersion());
            ur.setUpgrade_url(lastestversion.getPkgurl());
            return new ResponseEntity<>(ur, HttpStatus.OK);
        }
    }

}
