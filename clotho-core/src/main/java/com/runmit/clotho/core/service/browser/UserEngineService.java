package com.runmit.clotho.core.service.browser;

import com.runmit.clotho.core.domain.browser.UserEngine;
import com.runmit.clotho.core.mapper.browser.UserEngineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户搜索引擎关系 Service
 * @author lgz
 * @version 1.0
 * @date 2017-06-06
 */
@Service
@Transactional
public class UserEngineService{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserEngineService.class);

	@Autowired
	private UserEngineMapper userEngineMapper;


	/**
	 * 保存用户默认搜索引擎信息
	 * @param userEngine 用户默认搜索引擎对象
	 */
	@Transactional(readOnly = false)
	public int saveUserEngineInfo(UserEngine userEngine) throws Exception{
		return userEngineMapper.addUserEngine(userEngine);
	}

	/**
	 * 根据userId查找用户默认搜索引擎信息
	 * @param userId 用户ID
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public List<UserEngine> getUserDefaultEngine(int userId) throws Exception{
		return userEngineMapper.getUserDefaultEngine(userId);
	}

}
