package com.runmit.clotho.management.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.runmit.clotho.core.domain.admin.Admin;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.AdminService;
import com.runmit.clotho.management.domain.LoginUser;
import com.runmit.clotho.management.security.LDAPValidation;
import com.runmit.clotho.management.security.SecurityConstant;
import com.runmit.clotho.management.security.SessionUtil;
import java.io.IOException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author zhipeng.tian
 *
 * 2014年9月24日
 *
 */
@Controller
@Component
public class LoginController {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(LoginController.class);

    @Autowired
    private LDAPValidation ldap;
    @Autowired
    private AdminService adminService;
    @Value("${user.center.login.api.address}")
    private String loginUrl;
    @Value("${enable.user.center.switch}")
    private boolean usercenterSwitch;

    /**
     * user info checked
     *
     * @param request
     * @param model
     * @param uid
     * @param password
     * @return
     */
    @RequestMapping(value = "/loginValid")
    public @ResponseBody
    ExtStatusEntity userLogin(HttpServletRequest request, Model model,
            @RequestParam("name") String uid, @RequestParam("password") String password) {
        ExtStatusEntity resp = new ExtStatusEntity();
        Admin admin;
        if(usercenterSwitch && -1 != uid.indexOf("iv".toLowerCase()) && -1 != uid.indexOf(".") && !uid.contains(".com")) {
            admin = ldap.validAdmin(uid, password);
        } else {
            admin = this.verifyLoginByUC(uid, password);
        }
        Admin a = this.adminService.getAdminByUid(uid);
        
        if (null == admin) {
            resp.setMsg("LDAP账号密码错误或者不存在");
            resp.setSuccess(false);
        } else if (null == a) {
            resp.setMsg("后台管理账号不存在");
            resp.setSuccess(false);
        } else {
            admin.setId(a.getId());
            SessionUtil.setLoginAdmin(request, admin);
//			String gson = (new Gson()).toJson(admin);
            resp.setMsg(JSONObject.toJSONString(admin));
            resp.setSuccess(true);
            LOGGER.info(admin.getName() + " login");
        }

        return resp;
    }

    /**
     * logout
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/logout")
    public String userLogout(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        session.removeAttribute(SecurityConstant.ADMIN_SESSION_ATTRIBUTE);
        return "index";
    }

    private Admin verifyLoginByUC(String uid, String passwd) {
        LOGGER.info("loginUrl is "+loginUrl);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(loginUrl);
        httpPost.setHeader("superProjectId", "89");
        httpPost.setHeader("language", "zh_CN");
        httpPost.setHeader("clientId", "89");
        LoginUser user = new LoginUser();
        user.setAccount(uid);
        user.setPassword(DigestUtils.md5Hex(passwd));
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(user);
            LOGGER.info("user is " + json);
            StringEntity se = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(se);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            LOGGER.info("statusCode is " + statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(entity));
                int returnCode = jsonObject.getIntValue("rtn");
                LOGGER.info("returnCode is " + returnCode);
                if (returnCode == 0) {
                    JSONObject userJson = jsonObject.getJSONObject("userInfo");
                    Admin admin = new Admin();
                    admin.setId(userJson.getInteger("userid"));
                    admin.setName(userJson.getString("nickname") == null ? uid : userJson.getString("nickname"));
                    response.close();
                    return admin;
                }
            }
        } catch (IOException | IllegalStateException e) {
            LOGGER.error(String.format("Exception that User %s logined by UC appeared ", uid), e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException ex) {
                LOGGER.error(String.format("Exception that User %s logined by UC appeared ", uid), ex);
            }
        }
        return null;
    }

}
