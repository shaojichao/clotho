package com.runmit.clotho.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.runmit.clotho.core.domain.upgrade.UpgradePlan;
import com.runmit.clotho.core.domain.upgrade.UpgradePlanMemo;
import com.runmit.clotho.core.domain.upgrade.Version;
import com.runmit.clotho.core.service.VersionService;
import com.runmit.clotho.rest.common.RestConst;
import com.runmit.clotho.rest.domain.Upgrade;
import com.runmit.clotho.rest.domain.UpgradeResp;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * @author XP
 */
@RestController
@RequestMapping(value = "/upgrade")
public class UpgradeController {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(UpgradeController.class);

    @Autowired
    private VersionService versionService;

    /**
     * 获取当前升级版本
     * 参数说明在各自的RequestBody类中
     */
    @RequestMapping(value = "/getupgrade", method = RequestMethod.GET)
    @ApiOperation(value = "获取升级版本接口", notes = "用于客户端获取软件的可升级最新版本", httpMethod = "GET", consumes = "application/json", produces = "application/json", protocols = "http, https", nickname = "", response = Upgrade.class)
    @ApiResponses(value = {
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=-1,message = "当前版本无需升级"),
            @ApiResponse(code=1,message = "传入版本不存在"),
            @ApiResponse(code=2,message = "clientid无对应最新版本信息"),
            @ApiResponse(code=3,message = "关联版本无法找到基础版本信息")
    })
    public ResponseEntity<UpgradeResp> getupgrade(@RequestParam("version") String version,
                                                  @RequestParam("clientid") String clientidget,
                                                  @RequestParam(value="lang",required=false,defaultValue="zh") String lang) {
        Upgrade upgrade=new Upgrade();
        upgrade.setClientid(clientidget);
        upgrade.setVersion(version);
        UpgradeResp ur=new UpgradeResp();
        int clientid;
        try {
            clientid = Integer.parseInt(upgrade.getClientid());
            if(clientid<=0){
                LOGGER.error("getupgrade request error,clientid value error:"+clientid);
                return new ResponseEntity<>(ur, HttpStatus.BAD_REQUEST);
            }
        }catch (NumberFormatException e){
            LOGGER.error("getupgrade request error,clientid is not int"+e);
            return new ResponseEntity<>(ur, HttpStatus.BAD_REQUEST);
        }
        Version currentversion=versionService.getbyversion(upgrade.getVersion(),clientid);
        if(currentversion==null){
            LOGGER.error("getupgrade request error,current version is not exists:"+upgrade.getVersion());
            ur.setRtn(RestConst.RTN_GETUPGRADE_CURRENTVERSION_NOTEXIST);
            return new ResponseEntity<>(ur, HttpStatus.OK);
        }
        UpgradePlan upgradePlan=versionService.getUpgradePlanbyversion(upgrade.getVersion(),clientid);
        if(upgradePlan==null){//无对应关联升级版本,不升级
        	ur.setRtn(RestConst.RTN_GETUPGRADE_VERSION_LASTEST);
        	return new ResponseEntity<>(ur, HttpStatus.OK);
        }else{
            Version lastestversion=versionService.getbyserialno(upgradePlan.getUpgradeid());
            if(lastestversion==null){
                LOGGER.error("getupgrade request error,upgradeplan get version is not exists:"+upgrade.getVersion());
                ur.setRtn(RestConst.RTN_GETUPGRADE_PLANGETVERSION_NOTEXIST);
                return new ResponseEntity<>(ur, HttpStatus.OK);
            }
            UpgradePlanMemo memo = this.versionService.getUpgradePlanMemo(upgradePlan.getId(), lang);
            ur.setIntroduction(memo==null?"":memo.getMemo());
            ur.setRtn(RestConst.RTN_OK);
            ur.setShow_type(String.valueOf(upgradePlan.getShowtype()));
            ur.setUpgrade_type(String.valueOf(upgradePlan.getUpgradetype()));
            ur.setNew_version(lastestversion.getVersion());
            ur.setUpgrade_url(lastestversion.getPkgurl());
            return new ResponseEntity<>(ur, HttpStatus.OK);
        }
    }

}
