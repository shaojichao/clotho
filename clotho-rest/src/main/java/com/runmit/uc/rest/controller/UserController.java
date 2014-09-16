package com.runmit.uc.rest.controller;

import com.runmit.uc.core.common.MailContent;
import com.runmit.uc.core.common.PasswordEncoder;
import com.runmit.uc.core.domain.UserDeviceRela;
import com.runmit.uc.core.domain.UserDeviceRelaDel;
import com.runmit.uc.core.domain.UserInfo;
import com.runmit.uc.core.service.DeviceInfoService;
import com.runmit.uc.core.service.UserDeviceRelaDelService;
import com.runmit.uc.core.service.UserDeviceRelaService;
import com.runmit.uc.core.service.UserInfoService;
import com.runmit.uc.rest.common.RestConst;
import com.runmit.uc.rest.domain.*;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.Random;

/**
 * @author XP
 */
@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

    private static final Logger log = LoggerFactory
            .getLogger(UserController.class);

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserDeviceRelaService userDeviceRelaService;

    @Autowired
    private DeviceInfoService deviceInfoService;

    @Autowired
    private UserDeviceRelaDelService userDeviceRelaDelService;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${user.LimitDeviceRegUser}")
    private String LimitDeviceRegUser;

    @Value("${user.LimitUserDeviceRela}")
    private String LimitUserDeviceRela;

    @Value("${user.DeviceRegUser_Limit}")
    private int DeviceRegUser_Limit;//设备注册用户限制

    @Value("${user.UserDeviceRela_Limit}")
    private int UserDeviceRela_Limit;//用户绑定设备限制

    @Value("${user.UserLogin_Limit}")
    private int UserLogin_Limit;//用户同时在线数限制

    @Value("${user.SameUserSameDevice_Limit}")
    private int SameUserSameDevice_Limit;//同一账号同一设备重复绑定次数限制

    @Value("${user.ActiveMail_Subject}")
    private String ActiveMail_Subject;//激活邮件标题

    @Resource
    private MemcachedClient memcachedClient;

    /**
     * 给手机发送验证码
     * 参数说明在各自的RequestBody类中
     */
    @RequestMapping(value = "/verifymobile", method = RequestMethod.POST, consumes = {"application/json"})
    @ApiOperation(value = "手机校验码发送接口", notes = "用于注册等功能时验证手机", httpMethod = "POST", consumes = "application/json", produces = "application/json", protocols = "http, https", nickname = "", response = UserRegResp.class)
    @ApiResponses(value = {
            @ApiResponse(code=0,message = "成功")
    })
    public ResponseEntity<CommonResp> verifyMobile(@RequestBody @Valid VerifyMobile verifyMobile, BindingResult bindingResult) {
        CommonResp cr=new CommonResp();
        if (bindingResult.hasErrors()) {
//            FIXME:添加log输出错误类型
//            response.setRtn(bindingResult.getAllErrors().get(0).getDefaultMessage());
//            cr.setRtn(RestConst.rtn_BadRequest);
            return new ResponseEntity<>(cr, HttpStatus.BAD_REQUEST);
        }
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(r.nextInt(10));
        }
        try {
            memcachedClient.set(RestConst.NameSpace_UserController_verifymobile+ verifyMobile.getPhonenumber(), RestConst.MobileMessageExpire, sb.toString());
            log.debug((String)memcachedClient.get(RestConst.NameSpace_UserController_verifymobile+verifyMobile.getPhonenumber()));
            System.out.println((String)memcachedClient.get(RestConst.NameSpace_UserController_verifymobile+verifyMobile.getPhonenumber()));
        } catch (Exception e) {
//            FIXME:添加log输出错误类型
//            return RestConst.rtn_VerifyMobile_memcache_error;
//            cr.setRtn(RestConst.rtn_ServerFail);
            return new ResponseEntity<>(cr, HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        String message="abc:"+sb.toString();//TODO:短信内容是什么?
//        TODO:发送随机六位数字验证码到手机
//        sendMessage(verifyMobile.getPhonenumber(),message)
        cr.setRtn(RestConst.rtn_OK);
        return new ResponseEntity<>(cr, HttpStatus.OK);
    }

    /**
     * 注册用户
     * 版本号:1
     * 参数说明在各自的RequestBody类中
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "用户注册接口", notes = "注册用户成功同时绑定用户和注册时所用的设备,并返回用户id", httpMethod = "POST", consumes = "application/json", produces = "application/json", protocols = "http, https", nickname = "注册用户", response = UserRegResp.class)
    @ApiResponses(value = {
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=3,message = "注册成功,激活邮件发送失败"),
            @ApiResponse(code=9,message = "验证码超时"),
            @ApiResponse(code=11,message = "设备注册用户数超过限制"),
            @ApiResponse(code=12,message = "用户注册用的手机或者邮箱已存在"),
            @ApiResponse(code=13,message = "验证码错误"),
            @ApiResponse(code=14,message = "绑定设备失败"),
            @ApiResponse(code=18,message = "注册设备不存在")
            })
    public ResponseEntity<UserRegResp> getReg(@RequestBody @Valid UserReg userreg, BindingResult bindingResult) {
        UserRegResp response = new UserRegResp();
        if (bindingResult.hasErrors()) {
//            FIXME:添加log输出错误类型
//            response.setRtn(bindingResult.getAllErrors().get(0).getDefaultMessage());
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
//            response.setRtn(RestConst.rtn_BadRequest);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
//        校验设备是否存在
        if(deviceInfoService.getbysn(userreg.getDevicehwid())==null){
            response.setRtn(RestConst.rtn_UserReg_devicehwid_notexist);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
//        校验验证码是否有误或者是否已过期
        if (userreg.getUseridtype().equals(RestConst.UseridType_Mobile)) {
            if (userreg.getVerifycode() == null || userreg.getVerifycode().isEmpty()) {
//            FIXME:添加log输出错误类型
//                response.setRtn(RestConst.rtn_UserReg_verifycode_empty);
//                response.setRtn(RestConst.rtn_BadRequest);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            try {
                String verifycode =(String)memcachedClient.get(RestConst.NameSpace_UserController_verifymobile+userreg.getAccount());
                if (verifycode == null || verifycode.isEmpty()) {
                    response.setRtn(RestConst.rtn_UserReg_verifycode_timeout);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                if (!userreg.getVerifycode().equals(verifycode)) {
                    response.setRtn(RestConst.rtn_UserReg_verifycode_mismatching);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            } catch (Exception e) {
//            FIXME:添加log输出错误类型
//                response.setRtn(RestConst.rtn_UserReg_memcache_error);
//                response.setRtn(RestConst.rtn_ServerFail);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
//        校验设备注册用户限制
        if (Boolean.parseBoolean(LimitDeviceRegUser)&&deviceInfoService.getbysn(userreg.getDevicehwid()).getUserregcount() >= DeviceRegUser_Limit) {
            response.setRtn(RestConst.rtn_UserReg_DeviceRegUserLimit_Exceed);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
//        校验账号类型是否有误
        if ((!userreg.getUseridtype().equals(RestConst.UseridType_EMail)) && (!userreg.getUseridtype().equals(RestConst.UseridType_Mobile))) {
//            FIXME:添加log输出错误类型
//            response.setRtn(RestConst.rtn_UserReg_useridtype_valueerr);
//            response.setRtn(RestConst.rtn_BadRequest);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
//        校验账号是否存在
        if (!verifyaccount(userreg.getAccount(), userreg.getUseridtype())) {
            response.setRtn(RestConst.rtn_UserReg_MailorMobile_Exist);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
//        String psw= PasswordEncoder.encode(password);//由于传过来的密码已经MD5过了,无需再次加密
        UserInfo uiEntity = new UserInfo();
        UserDeviceRela udrEntity = new UserDeviceRela();
        uiEntity.setUserid(userreg.getAccount());
        uiEntity.setUseridtype(userreg.getUseridtype());
        uiEntity.setPassword(userreg.getPassword());
        uiEntity.setLocation(userreg.getLocation());
        uiEntity.setNickname(userreg.getNickname());
        if (RestConst.UseridType_EMail.equals(userreg.getUseridtype())) {
            uiEntity.setMail(userreg.getAccount());
            //TODO:邮箱需要激活注册,改造成enum
            uiEntity.setStatus("INACTIVE");
        } else if (RestConst.UseridType_Mobile.equals(userreg.getUseridtype())) {
            uiEntity.setMobile(userreg.getAccount());
//TODO:改造成enum类
            uiEntity.setStatus("ACTIVE");
        }
//TODO:公共方法添加默认字段
        uiEntity.setCreateby("restreg");
        uiEntity.setUpdateby("restreg");
        int id ;
        try {
            id = userInfoService.addUserInfo(uiEntity);
            response.setUserid(String.valueOf(id));
            udrEntity.setId(uiEntity.getId());
            udrEntity.setDevicesn(userreg.getDevicehwid());
            udrEntity.setDevicetype(userreg.getClientid());//终端id:1-android 2-iphone 3-androidpad 暂定
            userDeviceRelaService.addUserDeviceRela(udrEntity);
            deviceInfoService.addDeviceRegUser(userreg.getDevicehwid());
        }catch (Exception e) {
//            FIXME:添加log输出错误类型
//            response.setRtn(RestConst.rtn_UserReg_UserDeviceRelaAdd_fail);
//            response.setRtn(RestConst.rtn_ServerFail);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try{
            if (RestConst.UseridType_EMail.equals(userreg.getUseridtype())) {
                if (!sendActiveMail(id, userreg.getAccount())) {
                    response.setRtn(RestConst.rtn_UserReg_SendMail_fail);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
        }catch (MailException mailException){
            response.setRtn(RestConst.rtn_UserReg_SendMail_fail);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.setRtn(RestConst.rtn_OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 用户登陆,同时绑定Device
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {"application/json"})
    @ApiOperation(value = "用户登陆接口", notes = "已注册的用户进行登陆使用此接口", httpMethod = "POST", consumes = "application/json", produces = "application/json", protocols = "http, https", nickname = "", response = UserRegResp.class)
    @ApiResponses(value = {
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=4,message = "验证码超时"),
            @ApiResponse(code=9,message = "用户绑定设备数超过上限"),
            @ApiResponse(code=11,message = "验证码错误"),
            @ApiResponse(code=13,message = "用户同时在线数超过限制"),
            @ApiResponse(code=14,message = "同一用户同一设备绑定次数超过限制")
    })
    public ResponseEntity<UserLoginResp> getLogin(@RequestBody @Valid UserLogin userLogin, BindingResult bindingResult) {
        UserLoginResp ur = new UserLoginResp();
        String token = "";
        if (bindingResult.hasErrors()) {
            ur.setRtn(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<>(ur, HttpStatus.BAD_REQUEST);
        }
        try {
            UserInfo ui = userInfoService.getUserInfo(userLogin.getAccount());
            if (ui == null) {
                ur.setRtn(RestConst.rtn_UserLogin_account_notexist);
                return new ResponseEntity<>(ur, HttpStatus.OK);
            }
            //验证帐户绑定设备数限制
            if (Boolean.parseBoolean(LimitUserDeviceRela) && userDeviceRelaService.getRelabyId(ui.getId()).size() >= UserDeviceRela_Limit) {
                ur.setRtn(RestConst.rtn_UserLogin_userdevicerela_exceedlimit);
                return new ResponseEntity<>(ur, HttpStatus.OK);
            }
            token = PasswordEncoder.gettoken(ui.getUserid(), userLogin.getDevicehwid());
            UserDeviceRela userDeviceRela = userDeviceRelaService.getUserDeviceRela(ui.getId(), userLogin.getDevicehwid());
            if (userDeviceRela == null) {
                //该用户在该机器尚未登录的情况才需要验证用户同时在线数
                if(userDeviceRelaService.getLoginbyId(ui.getId()).size()>=UserLogin_Limit){
                    ur.setRtn(RestConst.rtn_UserLogin_userlogin_exceedlimit);
                    return new ResponseEntity<>(ur, HttpStatus.OK);
                }
                //            TODO:改成enum类型
                //            TODO:这里需要先激活否? --start
                //            if (ui.getStatus().equals("INACTIVE") && ui.getUseridtype().equals(RestConst.UseridType_EMail)) {
                //                if (!sendActiveMail(ui.getId(), ui.getUserid())) {
                //                    ur.setRtn(RestConst.rtn_UserLogin_SendMail_fail);
                //                    return ur;
                //                }
                //                ur.setRtn(RestConst.rtn_UserLogin_SendMail_success);
                //                return ur;
                //            }
                //            TODO:这里需要先激活否? --end
                if (ui.getUseridtype().equals(RestConst.UseridType_EMail) && (ui.getMobile() == null || ui.getMobile().isEmpty())) {
                    //TODO:优先采用手机验证登陆?
                }
                //            TODO:若是邮箱登陆需要验证否?
                //            添加验证手机验证码功能
                if (!userLogin.getVerifycode().isEmpty()) {
                    if (ui.getMobile() == null || ui.getMobile().isEmpty()) {
//            FIXME:添加log输出错误类型
//                        ur.setRtn(RestConst.rtn_UserLogin_Mobile_empty);
//                        ur.setRtn(RestConst.rtn_BadRequest);
                        return new ResponseEntity<>(ur, HttpStatus.BAD_REQUEST);
                    }
                    try {
                        String verifycode =(String)memcachedClient.get(RestConst.NameSpace_UserController_verifymobile+userLogin.getAccount());
                        if (verifycode == null || verifycode.isEmpty()) {
                            ur.setRtn(RestConst.rtn_UserLogin_verifycode_timeout);
                            return new ResponseEntity<>(ur, HttpStatus.OK);
                        }
                        if (!userLogin.getVerifycode().equals(verifycode)) {
                            ur.setRtn(RestConst.rtn_UserLogin_verifycode_mismatching);
                            return new ResponseEntity<>(ur, HttpStatus.OK);
                        }
                    } catch (Exception e) {
//            FIXME:添加log输出错误类型
//                        ur.setRtn(RestConst.rtn_UserLogin_memcache_error);
//                        ur.setRtn(RestConst.rtn_ServerFail);
                        return new ResponseEntity<>(ur, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
//添加同一用户同一设备绑定次数限制
                if(userDeviceRelaDelService.getUserDeviceRelaDel(ui.getId(),userLogin.getDevicehwid()).size()>=SameUserSameDevice_Limit){
                    ur.setRtn(RestConst.rtn_UserLogin_SameUserSameDevice_Exceedlimit);
                    return new ResponseEntity<>(ur, HttpStatus.OK);
                }
                userDeviceRela = new UserDeviceRela();
                userDeviceRela.setId(ui.getId());
                userDeviceRela.setDevicetype(userLogin.getClientid());
                userDeviceRela.setDevicesn(userLogin.getDevicehwid());
                userDeviceRela.setToken(token);
                userDeviceRelaService.addUserDeviceRela(userDeviceRela);
            } else {
                userDeviceRela.setToken(token);
                userDeviceRelaService.updateUserDeviceRela(userDeviceRela);
            }
            ur.setToken(token);
            UserInfoEntity rui = new UserInfoEntity(ui);
            ur.setUserInfo(rui);
        }catch (Exception e){
//            ur.setRtn(RestConst.rtn_ServerFail);
            return new ResponseEntity<>(ur, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ur.setRtn(RestConst.rtn_OK);
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }


    /**
     * 邮件发送接口
     */
    @RequestMapping(value = "/sendmail", method = RequestMethod.POST, consumes = {"application/json"})
    @ApiOperation(value = "激活邮件发送接口", notes = "针对激活邮件未收到或需要重发的情况进行激活邮件的重发", httpMethod = "POST", consumes = "application/json", produces = "application/json", protocols = "http, https", nickname = "", response = CommonResp.class)
    @ApiResponses(value = {
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=3,message = "登陆token错误"),
            @ApiResponse(code=4,message = "用户尚未登陆"),
            @ApiResponse(code=5,message = "无对应用户")
    })
    public ResponseEntity<CommonResp> sendMail(@RequestBody @Valid SendMail sendMail, BindingResult bindingResult) {
        CommonResp cr = new CommonResp();
        if (bindingResult.hasErrors()) {
//            FIXME:添加log输出错误类型
//            ucr.setRtn(bindingResult.getAllErrors().get(0).getDefaultMessage());
//            ucr.setRtn(RestConst.rtn_BadRequest);
            return new ResponseEntity<>(cr, HttpStatus.BAD_REQUEST);
        }
        int id;
        try {
            id=Integer.parseInt(sendMail.getUserid());
            if (id < 1) {
                log.error("userid in sendMail error, userid:" + sendMail.getUserid());
                return new ResponseEntity<>(cr, HttpStatus.BAD_REQUEST);
            }
        }catch (NumberFormatException e){
            log.error("userid in sendMail error, userid:" + sendMail.getUserid()+e);
            return new ResponseEntity<>(cr, HttpStatus.BAD_REQUEST);
        }
        if(userInfoService.getUserInfo(id)==null){
            cr.setRtn(RestConst.rtn_SendMail_user_notexists);
            return new ResponseEntity<>(cr, HttpStatus.OK);
        }
        if(!sendActiveMail(id,sendMail.getMailaddress())){
            cr.setRtn(RestConst.rtn_SendMail_send_fail);
        }else{
            cr.setRtn(RestConst.rtn_OK);
        }
        return new ResponseEntity<>(cr, HttpStatus.OK);
    }


    private boolean sendActiveMail(int id, String mailaddress) {
        String vali = PasswordEncoder.getvalidata(mailaddress);//邮箱激活码
        try {
            memcachedClient.set(RestConst.NameSpace_UserController_ActiveMail+mailaddress, RestConst.UserActiveMailExpire, vali);
            log.debug(RestConst.NameSpace_UserController_ActiveMail+":"+mailaddress+":"+(String)memcachedClient.get(RestConst.NameSpace_UserController_ActiveMail+mailaddress));
            System.out.println(RestConst.NameSpace_UserController_ActiveMail+":"+mailaddress+":"+(String)memcachedClient.get(RestConst.NameSpace_UserController_ActiveMail+mailaddress));
        } catch (Exception e) {
            return false;
        }
        MailContent mc = new MailContent();
        mc.setTo(mailaddress);
        mc.setSubject(ActiveMail_Subject);
        mc.setText("<a href=\"http://192.168.20.114:8080/uc/v1/user/active?id=" + id + "&vali=" + vali + "\">激活链接</a>");//TODO:改造到配置文件
        return sendMail(mc);
    }

    /**
     * 用户注销
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST, consumes = {"application/json"})
    @ApiOperation(value = "用户注销接口", notes = "已经登陆的用户进行注销操作", httpMethod = "POST", consumes = "application/json", produces = "application/json", protocols = "http, https", nickname = "", response = UserRegResp.class)
    @ApiResponses(value = {
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=3,message = "登陆token错误"),
            @ApiResponse(code=4,message = "用户尚未登陆")
    })
    public ResponseEntity<CommonResp> logout(@RequestBody @Valid UserLogout userLogout, BindingResult bindingResult) {
        CommonResp cr=new CommonResp();
        if (bindingResult.hasErrors()) {
//            FIXME:添加log输出错误类型
//            return (bindingResult.getAllErrors().get(0).getDefaultMessage());
//            cr.setRtn(RestConst.rtn_BadRequest);
            return new ResponseEntity<>(cr, HttpStatus.BAD_REQUEST);
        }
        UserDeviceRela udr = userDeviceRelaService.getUserDeviceRela(Integer.parseInt(userLogout.getUserid()), userLogout.getDevicehwid());
        if (udr == null) {
            cr.setRtn(RestConst.rtn_UserLogout_datanull);
            return new ResponseEntity<>(cr, HttpStatus.OK);
        }
        if (!udr.getToken().equals(userLogout.getToken())) {
            cr.setRtn(RestConst.rtn_UserLogout_tokenerror);
            return new ResponseEntity<>(cr, HttpStatus.OK);
        }
        udr.setToken("");
        try {
            userDeviceRelaService.updateUserDeviceRela(udr);
        } catch (Exception e) {
//            FIXME:添加log输出错误类型
//            return RestConst.rtn_UserLogout_deltokenfail;
//            cr.setRtn(RestConst.rtn_ServerFail);
            return new ResponseEntity<>(cr, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        cr.setRtn(RestConst.rtn_OK);
        return new ResponseEntity<>(cr, HttpStatus.OK);
    }

    /**
     * 鉴权
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST, consumes = {"application/json"})
    @ApiOperation(value = "鉴权接口", notes = "已经登陆的用户进行鉴权操作，保持用户在线状态", httpMethod = "POST", consumes = "application/json", produces = "application/json", protocols = "http, https", nickname = "", response = UserRegResp.class)
    @ApiResponses(value = {
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=3,message = "登陆token错误"),
            @ApiResponse(code=4,message = "用户尚未登陆"),
            @ApiResponse(code=5,message = "无对应用户")
    })
    public ResponseEntity<UserCheckResp> check(@RequestBody @Valid UserCheck userCheck, BindingResult bindingResult) {
        UserCheckResp ucr = new UserCheckResp();
        if (bindingResult.hasErrors()) {
//            FIXME:添加log输出错误类型
//            ucr.setRtn(bindingResult.getAllErrors().get(0).getDefaultMessage());
//            ucr.setRtn(RestConst.rtn_BadRequest);
            return new ResponseEntity<>(ucr, HttpStatus.BAD_REQUEST);
        }
        UserDeviceRela udr = userDeviceRelaService.getUserDeviceRela(Integer.parseInt(userCheck.getUserid()), userCheck.getDevicehwid());
        if (udr == null) {
            ucr.setRtn(RestConst.rtn_UserCheck_datanull);
            return new ResponseEntity<>(ucr, HttpStatus.OK);
        }
        if (!userCheck.getToken().equals(udr.getToken())) {
            ucr.setRtn(RestConst.rtn_UserCheck_tokenerror);
            return new ResponseEntity<>(ucr, HttpStatus.OK);
        }
        try {
            UserInfo ui = userInfoService.getUserInfo(Integer.parseInt(userCheck.getUserid()));
            UserInfoEntity usi = new UserInfoEntity(ui);
            ucr.setRtn(RestConst.rtn_OK);
            ucr.setUserInfo(usi);
            return new ResponseEntity<>(ucr, HttpStatus.OK);
        } catch (Exception e) {
            ucr.setRtn(RestConst.rtn_UserCheck_getuserinfofail);
            return new ResponseEntity<>(ucr, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 设备解绑
     */
    @RequestMapping(value = "/deldevice", method = RequestMethod.POST, consumes = {"application/json"})
    @ApiOperation(value = "设备解绑接口", notes = "对用户已经绑定的设备解除绑定", httpMethod = "POST", consumes = "application/json", produces = "application/json", protocols = "http, https", nickname = "", response = UserRegResp.class)
    @ApiResponses(value = {
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=3,message = "登陆token错误"),
            @ApiResponse(code=4,message = "用户尚未登陆")
    })
    public ResponseEntity<CommonResp> delDevice(@RequestBody @Valid DelDevice delDevice, BindingResult bindingResult) {
        CommonResp cr=new CommonResp();
        if (bindingResult.hasErrors()) {
//            FIXME:添加log输出错误类型
//            return (bindingResult.getAllErrors().get(0).getDefaultMessage());
//            cr.setRtn(RestConst.rtn_BadRequest);
            return new ResponseEntity<>(cr, HttpStatus.BAD_REQUEST);
        }
        UserDeviceRela udr = userDeviceRelaService.getUserDeviceRela(Integer.parseInt(delDevice.getUserid()), delDevice.getDevicehwid());
        if (udr == null) {
            cr.setRtn(RestConst.rtn_DelDevice_datanull);
            return new ResponseEntity<>(cr, HttpStatus.OK);
        }
        if (!udr.getToken().equals(delDevice.getToken())) {
            cr.setRtn(RestConst.rtn_DelDevice_tokenerror);
            return new ResponseEntity<>(cr, HttpStatus.OK);
        }
//        TODO:是否需要添加验证过程:手机校验或者邮件验证
        try {
            userDeviceRelaService.deleteUserDeviceRela(Integer.parseInt(delDevice.getUserid()), delDevice.getDevicehwid());
            UserDeviceRelaDel udrd=new UserDeviceRelaDel();
            udrd.setId(Integer.parseInt(delDevice.getUserid()));
            udrd.setDevicesn(delDevice.getDevicehwid());
            udrd.setStatus("ACTIVE");//全部默认,后续再说
            userDeviceRelaDelService.addUserDeviceRelaDel(udrd);
        } catch (Exception e) {
//            FIXME:添加log输出错误类型
//            return RestConst.rtn_DelDevice_submitfail;
//            cr.setRtn(RestConst.rtn_ServerFail);
            return new ResponseEntity<>(cr, HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        TODO:是否需要在userinfo表中记录解绑次数?
        cr.setRtn(RestConst.rtn_OK);
        return new ResponseEntity<>(cr, HttpStatus.OK);
    }


    /**
     * 根据激活信息对账号进行激活
     */
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    @ApiOperation(value = "账号激活接口", notes = "邮箱用户账号激活", httpMethod = "POST", consumes = "application/json", produces = "application/json", protocols = "http, https", nickname = "", response = UserRegResp.class)
    public ResponseEntity<String> getActive(
            @RequestParam("id") int id,
            @RequestParam("vali") String vali) {
        // 开始log输出
        log.info(
                "Calling getActive with userid={},vali={}",
                new Object[]{id, vali});
        UserInfo ui=userInfoService.getUserInfo(id);
        if (ui==null){
            return new ResponseEntity<>(RestConst.rtn_getActive_userinfo_empty, HttpStatus.OK);
        }
        if(ui.getMail()==null||ui.getMail().isEmpty()){
//            TODO:无对应用户
            return new ResponseEntity<>(RestConst.rtn_getActive_userinfo_empty, HttpStatus.OK);
        }
        try {
            log.debug(RestConst.NameSpace_UserController_ActiveMail+":"+ui.getMail()+":"+(String)memcachedClient.get(RestConst.NameSpace_UserController_ActiveMail+ui.getMail()));
            System.out.println("=========="+RestConst.NameSpace_UserController_ActiveMail+":"+ui.getMail()+":"+(String)memcachedClient.get(RestConst.NameSpace_UserController_ActiveMail+ui.getMail()));
            String verifycode =(String)memcachedClient.get(RestConst.NameSpace_UserController_ActiveMail+ui.getMail());
            if (verifycode == null || verifycode.isEmpty()) {
                return new ResponseEntity<>(RestConst.rtn_getActive_verifycode_timeout, HttpStatus.OK);
            }
            if (!vali.equals(verifycode)) {
                return new ResponseEntity<>(RestConst.rtn_getActive_verifycode_mismatching, HttpStatus.OK);
            }
//            TODO:改成enum
            ui.setStatus("ACTIVE");
            userInfoService.updateUser(ui);
        } catch (Exception e) {
//            FIXME:添加log输出错误类型
//                response.setRtn(RestConst.rtn_getActive_memcache_error);
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(RestConst.rtn_getActive_success, HttpStatus.OK);
    }

    /*    校验账号是否存在*/
    private boolean verifyaccount(String account, String accounttype) {
        if (accounttype.equals(RestConst.UseridType_EMail) && userInfoService.getbyMail(account).size() > 0) {
            return false;
        }
        if (accounttype.equals(RestConst.UseridType_Mobile) && userInfoService.getbyMobile(account).size() > 0) {
            return false;
        }
        return true;
    }

    public boolean sendMail(MailContent mc){
        MimeMessage msg = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码，true表示为multipart邮件
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
            helper.setFrom("runmit_test@163.com");
            helper.setTo(mc.getTo()); // 邮件接收地址
            helper.setSubject(mc.getSubject()); // 主题
            helper.setText(mc.getText(),mc.isIsHtmlMail()); // 邮件内容，注意加参数true，表示启用html格式
        } catch (Exception e) {
            log.error("set MimeMessage error",e);
            return false;
        }
        mailSender.send(msg); // 发送邮件
        return true;
    }

}
