package com.runmit.uc.management.controller;

import com.runmit.uc.core.domain.UserInfo;
import com.runmit.uc.core.service.UserInfoService;
import com.runmit.uc.core.service.UserRoleRelaService;
import com.runmit.uc.management.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author TianLiang
 */
@Controller
@RequestMapping(value = "/user")
public class UserManageController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserRoleRelaService urService;

    @RequestMapping(value = "/usermanage")
    public String getUserManage(HttpServletRequest request, Model model) {
        List<UserInfo> uis = userInfoService.getAllUserInfo();
        model.addAttribute("rv", new ResponseVo<UserInfo>(uis));
        return "user/usermanage/index";
    }

    @RequestMapping(value = "/groupmanage")
    public String getGroupManage() {
        return "/user/groupmanage/index";
    }

    @RequestMapping(value = "/userdelete")
    public void deluser(@RequestParam(value = "id", required = false) Integer id, HttpServletRequest request, Model model) {
        try{
            userInfoService.deleteUser(id);
        }catch (Exception e){

        }

    }

    @RequestMapping(value = "/getuserinfo",method = RequestMethod.GET)
    public @ResponseBody
    List<UserInfo> listuser(
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletRequest request, Model model) throws Exception {
        List<UserInfo> userinfos = userInfoService.getAllUserInfo();
        return userinfos;
    }
}
