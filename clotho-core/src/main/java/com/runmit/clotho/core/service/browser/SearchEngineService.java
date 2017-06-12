package com.runmit.clotho.core.service.browser;

import com.runmit.clotho.core.domain.browser.SearchEngine;
import com.runmit.clotho.core.mapper.browser.SearchEngineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 搜索引擎Service
 * @author lgz
 * @version 1.0
 * @date 2017-06-06
 */
@Service
@Transactional
public class SearchEngineService{
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchEngineService.class);

	@Value("${file.download.url}")
	private String downloadUrl;

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
		List<SearchEngine> list = engineMapper.getList(start, limit);
		for(SearchEngine se: list){
			se.setImgUploadUrl(downloadUrl);
		}
		return list;
	}

	/**
	 * 查找搜索引擎信息总条数
	 * @return
	 */
	@Transactional(readOnly = true)
	public long getCount(){
		return engineMapper.getCounts();
	}

	/**
	 * 保存搜索引擎信息
	 * @param engine
	 */
	@Transactional(readOnly = false)
	public void saveEngineInfo(SearchEngine engine) throws Exception{
		if(engine.getId() == null){
			engineMapper.addSearchEngine(engine);
		}else{
			engineMapper.updateSearchEngine(engine);
		}
	}

	@Transactional(readOnly = true)
	public List<SearchEngine> getDefaultEngine(int status){
		return engineMapper.getDefaultEngine(status);
	}

	/**
	 * 根据ID删除搜索引擎信息
	 * @param id
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public int delEngine(int id) throws Exception{
		return engineMapper.deleteById(id);
	}

	/**
	 * 根据ID查找搜索引擎信息
	 * @param id 引擎ID
	 * @return
	 */
	@Transactional(readOnly = true)
	public SearchEngine selectById(int id) throws Exception{
		return engineMapper.selectById(id);
	}

	/**
	 * 查找所有搜索引擎信息
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<SearchEngine> selectAll(){
		return engineMapper.selectAll();
	}

}
