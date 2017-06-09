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
	 * 分页查找用户搜索引擎信息集合
	 * @param account 用户账号
	 * @param engineId 搜索引擎ID
	 * @param start 开始页
	 * @param limit 每页大小
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<UserEngine> getList(String account,Integer engineId,int start, int limit){
		return userEngineMapper.getList(account,engineId,start, limit);
	}

	/**
	 * 分页查找用户搜索引擎信息总条数
	 * @return
	 */
	@Transactional(readOnly = true)
	public long getCount(String account,Integer engineId){
		return userEngineMapper.getCounts(account,engineId);
	}

	/**
	 * 添加用户默认搜索引擎信息
	 * @param userEngine 用户默认搜索引擎对象
	 */
	@Transactional(readOnly = false)
	public int saveUserEngineInfo(UserEngine userEngine) throws Exception{
		return userEngineMapper.addUserEngine(userEngine);
	}

	/**
	 * 修改用户默认搜索引擎信息
	 * @param userEngine 用户默认搜索引擎对象
	 */
	@Transactional(readOnly = false)
	public int modifyUserEngineInfo(UserEngine userEngine) throws Exception{
		return userEngineMapper.updateUserEngine(userEngine);
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
