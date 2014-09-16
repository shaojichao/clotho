package com.runmit.clotho.rest.common;

/**
 * Created by gg on 2014/8/21.
 */
public class RestConst {

    public final static String UseridType_EMail = "1";//邮箱类型
    public final static String UseridType_Mobile= "2";//手机

//  memcache的key前缀
    public final static String NameSpace_UserController_verifymobile="UserController_verifymobile_";
    public final static String NameSpace_UserController_ActiveMail="UserController_ActiveMail_";

    public final static int MobileMessageExpire=300;//短信验证码失效时间
    public final static int UserActiveMailExpire=3600;//邮箱链接失效时间

/*
接口通用返回值类型的说明
 */
    public final static String rtn_OK = "0";//成功返回值
    public final static String rtn_BadRequest = "400";//请求错误
    public final static String rtn_ServerFail = "500";//服务器内部错误

    public final static String rtn_paramlost = "51";//参数缺失
    public final static String rtn_urinotexists = "52";//服务器无对应URI


/*
用户注册返回信息
 */
    public final static String rtn_UserReg_useridtype_empty = "1";//useridtype为空
    public final static String rtn_UserReg_useridtype_valueerr = "2";//useridtype传值有误:1-邮箱 2-手机
    public final static String rtn_UserReg_SendMail_fail="3";//注册成功,激活邮件发送失败
    public final static String rtn_UserReg_password_size = "4";//密码长度不为32
    public final static String rtn_UserReg_clientid_empty = "5";//产品id为空
    public final static String rtn_UserReg_memcache_error="6";//memcache程序错误
    public final static String rtn_UserReg_devicehwid_empty = "7";//硬件sn为空
    public final static String rtn_UserReg_verifycode_empty = "8";//验证码为空
    public final static String rtn_UserReg_verifycode_timeout = "9";//验证码超时
    public final static String rtn_UserReg_account_empty = "10";//账号为空
    public final static String rtn_UserReg_DeviceRegUserLimit_Exceed="11";//设备注册用户数超过限制
    public final static String rtn_UserReg_MailorMobile_Exist="12";//用户注册用的手机或者邮箱以存在
    public final static String rtn_UserReg_verifycode_mismatching = "13";//验证码错误
    public final static String rtn_UserReg_UserDeviceRelaAdd_fail="14";//绑定设备失败
    public final static String rtn_UserReg_account_formatillegal = "15";//账号格式非法,手机或者邮箱
    public final static String rtn_UserReg_clientid_valueerr = "16";//产品id值有误:1-android 2-iphone 3-androidpad
    public final static String rtn_UserReg_location_empty="17";//注册时区域为空
    public final static String rtn_UserReg_devicehwid_notexist="18";//注册设备不存在

/*
用户登陆返回信息
 */
    public final static String rtn_UserLogin_account_notexist = "1";//账号类型为空
    public final static String rtn_UserLogin_password_size = "2";//密码长度不为32
    public final static String rtn_UserLogin_devicehwid_empty = "3";//硬件sn为空
    public final static String rtn_UserLogin_verifycode_timeout = "4";//验证码超时
    public final static String rtn_UserLogin_account_empty = "5";//账号为空
    public final static String rtn_UserLogin_clientid_empty = "6";//账号类型为空
    public final static String rtn_UserLogin_SendMail_fail="7";//发送激活邮件失败
    public final static String rtn_UserLogin_SendMail_success="8";//发送激活邮件成功
    public final static String rtn_UserLogin_userdevicerela_exceedlimit="9";//用户绑定设备数超过上限
    public final static String rtn_UserLogin_Mobile_empty="10";//验证码非空,账号手机号为空
    public final static String rtn_UserLogin_verifycode_mismatching = "11";//验证码错误
    public final static String rtn_UserLogin_memcache_error="12";//memcache程序错误
    public final static String rtn_UserLogin_userlogin_exceedlimit="13";//用户登录数超过限制
    public final static String rtn_UserLogin_SameUserSameDevice_Exceedlimit="14";//同一账号同一设备绑定次数限制

    /*
    用户注销返回信息
     */
    public final static String rtn_UserLogout_userid_empty = "1";//用户ID为空
    public final static String rtn_UserLogout_token_empty = "2";//token为空
    public final static String rtn_UserLogout_tokenerror="3";//token有误
    public final static String rtn_UserLogout_datanull="4";//用户和设备绑定数据为空
    public final static String rtn_UserLogout_deltokenfail="5";//作废token失败
    public final static String rtn_UserLogout_devicehwid_empty = "6";//硬件sn为空

    /*
    用户鉴权返回信息
     */
    public final static String rtn_UserCheck_userid_empty = "1";//用户ID为空
    public final static String rtn_UserCheck_token_empty = "2";//token为空
    public final static String rtn_UserCheck_tokenerror="3";//token有误
    public final static String rtn_UserCheck_datanull="4";//用户和设备绑定数据为空
    public final static String rtn_UserCheck_getuserinfofail="5";//用户信息为空
    public final static String rtn_UserCheck_devicehwid_empty = "6";//硬件sn为空

    /*
    设备解绑返回信息
     */
    public final static String rtn_DelDevice_userid_empty = "1";//用户ID为空
    public final static String rtn_DelDevice_token_empty = "2";//token为空
    public final static String rtn_DelDevice_tokenerror="3";//token有误
    public final static String rtn_DelDevice_datanull="4";//用户和设备绑定数据为空
    public final static String rtn_DelDevice_submitfail="5";//数据提交失败
    public final static String rtn_DelDevice_devicehwid_empty = "6";//硬件sn为空

    /*
手机校验信息
 */
    public final static String rtn_VerifyMobile_phonenumber_empty = "1";//手机号为空
    public final static String rtn_VerifyMobile_memcache_error="2";//存储校验码失败

    /*
邮件激活信息
 */
    public final static String rtn_getActive_userinfo_empty = "active fail";//无对应用户信息
    public final static String rtn_getActive_verifycode_timeout="verifycode timeout";//验证码过期
    public final static String rtn_getActive_verifycode_mismatching="verifycode mismatch";//验证码无效
    public final static String rtn_getActive_success="active success";//激活成功

    /*
发送激活邮件
 */
    public final static String rtn_SendMail_userid_empty = "1";//用户id为空
    public final static String rtn_SendMail_mailaddress_empty="2";//邮箱为空
    public final static String rtn_SendMail_mailaddress_invalid="3";//邮箱无效
    public final static String rtn_SendMail_send_fail="4";//邮件发送失败
    public final static String rtn_SendMail_user_notexists="5";//用户为空



}
