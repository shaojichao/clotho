package com.runmit.clotho.management.controller.browser;

import com.runmit.clotho.core.domain.browser.SearchEngine;
import com.runmit.clotho.core.domain.browser.UserEngine;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.browser.SearchEngineService;
import com.runmit.clotho.core.service.browser.UserEngineService;
import com.runmit.clotho.management.security.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户搜索引擎Controller
 * @author lgz
 * @version 1.0
 * @date 2017-06-06
 */
@RestController
public class UserEngineController{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserEngineController.class);

	@Autowired
	private UserEngineService userEngineService;
	@Autowired
	private SearchEngineService engineService;

	/**
	 * 搜索引擎下拉框查询
	 * @return
	 */
	@RequestMapping(value = "/clotho/userEngine/selectEngineList.do")
	public List<SearchEngine> selectEngineList(){
		return engineService.selectAll();
	}

	/**
	 * 用户搜索引擎列表查询
	 * @param account 用户账号
	 * @param engineId 引擎ID
	 * @param start 开始页
	 * @param limit 每页大小
	 * @return
	 */
	@RequestMapping(value = "/clotho/userEngine/userEngineList.do")
	public ExtEntity<UserEngine> userEngineList(
			@RequestParam(required = false) String account,
			@RequestParam(required = false) Integer engineId,
			@RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit){
		ExtEntity<UserEngine> datas = new ExtEntity<UserEngine>();

		List<UserEngine> list = userEngineService.getList(account,engineId,start, limit);
		datas.setRows(list);
		datas.setResult(userEngineService.getCount(account,engineId));
		LOGGER.info("----------- UserEngineController.userEngineList");
		return datas;
	}

	/**
	 * 修改用户搜索引擎信息
	 * @param userEngine 用户搜索引擎信息对象
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/clotho/userEngine/modifyUserEngine.do", method = RequestMethod.POST)
	public ExtStatusEntity modifyUserEngine(UserEngine userEngine,HttpServletRequest request){
		ExtStatusEntity entity = new ExtStatusEntity();
		try {
			userEngine.setUpdateBy(SessionUtil.getLoginAdminName(request));
			int rel=userEngineService.modifyUserEngineInfo(userEngine);
			if(rel > 0){
				entity.setMsg("succeed");
				entity.setSuccess(true);
			}else{
				entity.setMsg("修改失败!");
				entity.setSuccess(false);
			}
		} catch (Exception ex) {
			LOGGER.error("--------- UserEngineController.modifyUserEngine error", ex);
			entity.setMsg("修改失败!");
			entity.setSuccess(false);
		}
		return entity;
	}

}
