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

import java.text.SimpleDateFormat;
import java.util.Date;

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
            ur.setFilesize(lastestversion.getFilesize());
            return new ResponseEntity<>(ur, HttpStatus.OK);
        }
    }

    /**
     * 获取当前升级版本
     * 参数说明在各自的RequestBody类中
     */
    @RequestMapping(value = "/getOtaUpgrade", method = RequestMethod.GET)
    @ApiOperation(value = "获取OTA升级版本接口", notes = "用于客户端获取软件的可升级最新版本", httpMethod = "GET", consumes = "application/json", produces = "application/json", protocols = "http, https", nickname = "", response = Upgrade.class)
    @ApiResponses(value = {
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=-1,message = "当前版本无需升级"),
            @ApiResponse(code=1,message = "传入版本不存在"),
            @ApiResponse(code=2,message = "clientid无对应最新版本信息"),
            @ApiResponse(code=3,message = "关联版本无法找到基础版本信息")
    })
    public ResponseEntity<UpgradeResp> getOtaUpgrade(@RequestParam("clientVersion") String clientVersion,@RequestParam("brand") String brand,@RequestParam("model") String model,
                                                     @RequestParam("country") String country,@RequestParam("hardwareVersion") String hardwareVersion,@RequestParam("firmwareVersion") String firmwareVersion,
                                                     @RequestParam(value="lang",required=false,defaultValue="zh") String lang) {
        UpgradeResp ur=new UpgradeResp();
        // 判断客户端版本号
        if ("1.0.0".equals(clientVersion)){
            Upgrade upgrade=new Upgrade();
            // OTA升级版本号
            upgrade.setClientid("20");
            // 固件版本号
            upgrade.setVersion(firmwareVersion);
            int clientId = 0;
            try {
                clientId = Integer.parseInt(upgrade.getClientid());
                if(clientId<=0){
                    LOGGER.error("getOtaUpgrade request error,clientId value error:"+clientId);
                    ur.setRtmsg("当前版本无需升级");
                    return new ResponseEntity<>(ur, HttpStatus.BAD_REQUEST);
                }
            }catch (NumberFormatException e){
                LOGGER.error("getOtaUpgrade request error,clientId is not int"+e);
                return new ResponseEntity<>(ur, HttpStatus.BAD_REQUEST);
            }
            // 校验是否存在OTA新的升级版本信息
            Version currentOtaVersion=versionService.getOtaVersion(brand, model,country,hardwareVersion,firmwareVersion);
            if(currentOtaVersion==null){
                LOGGER.error("getOtaUpgrade request error,current version does not exists:"+upgrade.getVersion());
                ur.setRtn(RestConst.RTN_GETUPGRADE_CURRENTVERSION_NOTEXIST);
                ur.setRtmsg("传入版本不存在");
                return new ResponseEntity<>(ur, HttpStatus.OK);
            }
            UpgradePlan upgradePlan=versionService.getOtaUpgradePlanbyversion(currentOtaVersion.getVersion(), clientId,
                    currentOtaVersion.getBrand(),currentOtaVersion.getModel(),currentOtaVersion.getCountry(),currentOtaVersion.getHardwareVersion());
            if(upgradePlan==null){//无对应关联升级版本,不升级
                ur.setRtn(RestConst.RTN_GETUPGRADE_VERSION_LASTEST);
                ur.setRtmsg("当前版本无需升级");
                return new ResponseEntity<>(ur, HttpStatus.OK);
            }else{
                Version lastVersion=versionService.getbyserialno(upgradePlan.getUpgradeid());
                if(lastVersion==null){
                    LOGGER.error("getOtaUpgrade request error,upgradePlan get version does not exists:"+upgrade.getVersion());
                    ur.setRtn(RestConst.RTN_GETUPGRADE_PLANGETVERSION_NOTEXIST);
                    ur.setRtmsg("关联版本无法找到基础版本信息");
                    return new ResponseEntity<>(ur, HttpStatus.OK);
                }
                UpgradePlanMemo memo = this.versionService.getUpgradePlanMemo(upgradePlan.getId(), lang);
                ur.setIntroduction(memo == null ? "" : memo.getMemo());
                ur.setRtn(RestConst.RTN_OK);
                ur.setRtmsg("成功");
                ur.setShow_type(String.valueOf(upgradePlan.getShowtype()));
                ur.setUpgrade_type(String.valueOf(upgradePlan.getUpgradetype()));
                ur.setNew_version(lastVersion.getVersion());
                ur.setUpgrade_url(lastVersion.getPkgurl());
                ur.setFilesize(lastVersion.getFilesize());
                // OTA升级包指定名
                StringBuffer upgradeName = new StringBuffer();
                // 产品品牌
                upgradeName.append(lastVersion.getBrand()).append("-");
                // 产品型号
                upgradeName.append(lastVersion.getCountry()).append("-");
                // 销售国家
                upgradeName.append(lastVersion.getModel()).append("-");
                // 硬件版本号
                upgradeName.append(lastVersion.getHardwareVersion()).append("-");
                // 固件版本号
                upgradeName.append(lastVersion.getVersion()).append("-");
                // 固件编译或发布时间
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                String dateString = formatter.format(lastVersion.getCreatetime());
                upgradeName.append(dateString).append(".zip");
                ur.setUpgrade_name(upgradeName.toString());
                ur.setLatitude(lastVersion.getLatitude());
                return new ResponseEntity<>(ur, HttpStatus.OK);
            }
        }else{
            LOGGER.error("getOtaUpgrade request error,current clientVersion does not exists:"+ clientVersion);
            ur.setRtn(RestConst.RTN_GETUPGRADE_CURRENTVERSION_NOTEXIST);
            ur.setRtmsg("传入版本不存在");
            return new ResponseEntity<>(ur, HttpStatus.OK);
        }
    }

}
