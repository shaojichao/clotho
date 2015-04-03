package com.runmit.clotho.rest.controller;

import com.runmit.clotho.core.domain.userfeedback.UserFeedback;
import com.runmit.clotho.core.service.UserFeedbackService;
import com.runmit.clotho.rest.common.RestConst;
import com.runmit.clotho.rest.domain.Comment;
import com.runmit.clotho.rest.domain.CommonResp;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping(value = "/comment")
public class CommentController {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(CommentController.class);

    @Autowired
    private UserFeedbackService userFeedbackService;

    /**
     * 获取当前升级版本
     * 参数说明在各自的RequestBody类中
     */
    @RequestMapping(value = "/newcomment", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "提交用户评论", notes = "用于客户端提交用户评论", httpMethod = "POST", consumes = "application/json", produces = "application/json", protocols = "http, https", nickname = "", response = Comment.class)
    @ApiResponses(value = {
            @ApiResponse(code=0,message = "成功")
    })
    public ResponseEntity<CommonResp> comment(@RequestBody @Valid Comment comment,BindingResult bindingResult) {
        CommonResp cr=new CommonResp();
        if (bindingResult.hasErrors()) {
            LOGGER.error("comment request error,request error code:" + bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<>(cr, HttpStatus.BAD_REQUEST);
        }
        UserFeedback userFeedback=getFeedback(comment);
        userFeedbackService.addUserFeedback(userFeedback);
        cr.setRtn(RestConst.RTN_OK);
        return new ResponseEntity<>(cr, HttpStatus.OK);
    }

    private UserFeedback getFeedback(Comment comment){
        UserFeedback userFeedback=new UserFeedback();
        if(StringUtils.isNotBlank(comment.getHwid())){
            userFeedback.setHwid(comment.getHwid());
        }
        userFeedback.setUdid(comment.getUdid());
        userFeedback.setClientId(comment.getClientId());
        userFeedback.setWifimac(comment.getWifimac());
        userFeedback.setWirelesssmac(comment.getWirelesssmac());
        userFeedback.setWiremac(comment.getWiremac());
        userFeedback.setOs(comment.getOs());
        userFeedback.setOsver(comment.getOsver());
        userFeedback.setDevice(comment.getDevice());
        userFeedback.setArea(comment.getArea());
        userFeedback.setLanguage(comment.getLanguage());
        userFeedback.setImei(comment.getImei());
        userFeedback.setIdfv(comment.getIdfv());
        userFeedback.setAppkey(comment.getAppkey());
        userFeedback.setAppver(comment.getAppver());
        userFeedback.setUid(comment.getUid());
        userFeedback.setDevicebrand(StringUtils.substring(comment.getDevicebrand(), 0, 500));
        userFeedback.setDevicedevice(StringUtils.substring(comment.getDevicedevice(), 0, 500));
        userFeedback.setDevicemodel(StringUtils.substring(comment.getDevicemodel(), 0, 500));
        userFeedback.setDevicehardware(StringUtils.substring(comment.getDevicehardware(), 0, 500));
        userFeedback.setDeviceid(StringUtils.substring(comment.getDeviceid(), 0, 500));
        userFeedback.setDeviceserial(StringUtils.substring(comment.getDeviceserial(), 0, 500));
        if(StringUtils.isNotBlank(comment.getRo())){
            userFeedback.setRo(comment.getRo());
        }
        userFeedback.setChannel(comment.getChannel());
        userFeedback.setDts(comment.getDts());
        userFeedback.setContact(comment.getContact());
        userFeedback.setContent(comment.getContent());
        return userFeedback;
    }

}
