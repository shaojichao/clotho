package com.runmit.clotho.core.service.browser;

import com.runmit.clotho.core.domain.browser.SearchEngine;
import com.runmit.clotho.core.mapper.browser.SearchEngineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class SearchEngineService{
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchEngineService.class);

	@Autowired
	private SearchEngineMapper engineMapper;

	/**
	 * 分页查找搜索引擎信息集合
	 * @param start
	 * @param limit
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<SearchEngine> getList(int start, int limit){
		return engineMapper.getList(start, limit);
	}

	/**
	 * 查找搜索引擎信息总条数
	 * @return
	 */
	@Transactional(readOnly = true)
	public long getCount(){
		return engineMapper.getCounts();
	}


}
