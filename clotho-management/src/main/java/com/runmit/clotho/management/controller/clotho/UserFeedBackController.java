package com.runmit.clotho.management.controller.clotho;

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
@RequestMapping(value = "/clotho/userfeedback")
public class UserFeedBackController {
	private static final Logger LOGGER = LoggerFactory
            .getLogger(UserFeedBackController.class);
	
	@Autowired
    private UserFeedbackService userFeedbackService;
	
	/**
	 * user feed back list
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/list.do")
	public @ResponseBody ExtEntity<UserFeedback> getUserFeedBacks(
			@RequestParam(value = "start", required = false,defaultValue="0") Integer start,
            @RequestParam(value = "limit", required = false,defaultValue="20") Integer limit,
            @RequestParam(value = "se_clientId", required = false, defaultValue = "1") Integer clientid) {
		
		ExtEntity<UserFeedback> listdata = new ExtEntity<UserFeedback>();
		List<UserFeedback> list = userFeedbackService.getList(clientid, start, limit);
		listdata.setRows(list);
		listdata.setResult(userFeedbackService.getCount(clientid));
		
		LOGGER.info("getUserFeedBacks");
		return listdata;
	}
}
