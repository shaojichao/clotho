package com.runmit.clotho.management.controller.browser;

import com.runmit.clotho.core.domain.browser.SearchEngine;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.service.browser.SearchEngineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class SearchEngineController{
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchEngineController.class);

	@Autowired
	private SearchEngineService engineService;

	/**
	 * 搜索引擎列表查询
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/clotho/engine/engineList.do")
	public ExtEntity<SearchEngine> getEngineList(
			@RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit){
		ExtEntity<SearchEngine> datas = new ExtEntity<SearchEngine>();

		List<SearchEngine> list = engineService.getList(start, limit);
		datas.setRows(list);
		datas.setResult(engineService.getCount());
		LOGGER.info("----------- getEngineList");
		return datas;
	}

}
