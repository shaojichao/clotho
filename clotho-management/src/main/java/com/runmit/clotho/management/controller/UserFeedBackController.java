package com.runmit.clotho.management.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runmit.clotho.core.domain.userfeedback.UserFeedback;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.service.UserFeedbackService;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月26日
 */
@Controller
@Component
@RequestMapping(value = "/userfeedback")
public class UserFeedBackController {
	private static final Logger log = LoggerFactory
            .getLogger(UserFeedBackController.class);
	
	@Autowired
    private UserFeedbackService userFeedbackService;
	
	@RequestMapping(value = "/list.do")
	public @ResponseBody ExtEntity<UserFeedback> getUserFeedBacks(
			@RequestParam(value = "start", required = false,defaultValue="0") Integer start,
            @RequestParam(value = "limit", required = false,defaultValue="20") Integer limit) {
		
		
		
		ExtEntity<UserFeedback> listdata = new ExtEntity<UserFeedback>();
		
		List<UserFeedback> list = userFeedbackService.getList(start, limit);
		
		listdata.setRows(list);
		listdata.setResult(userFeedbackService.getCount());
		
		log.info("getUserFeedBacks");
		return listdata;
	}
}
