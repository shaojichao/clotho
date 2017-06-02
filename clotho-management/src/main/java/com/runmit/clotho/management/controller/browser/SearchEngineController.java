package com.runmit.clotho.management.controller.browser;

import com.runmit.clotho.core.domain.StatusEnum;
import com.runmit.clotho.core.domain.browser.SearchEngine;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.browser.SearchEngineService;
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

	@RequestMapping(value = "/clotho/engine/saveEngine.do", method = RequestMethod.POST)
	public ExtStatusEntity saveEngine(SearchEngine engine,HttpServletRequest request){
		ExtStatusEntity entity = new ExtStatusEntity();
		//验证默认搜索只有一个
		List<SearchEngine> defaultList = engineService.getDefaultEngine(StatusEnum.STATUS_Y.getType());
		if(defaultList.size() > 1){
			entity.setMsg("存在多个默认搜索引擎，信息异常!");
			entity.setSuccess(false);
			return entity;
		}
		if(engine.getId() == null){
			if(defaultList.size() == 1 && engine.getStatus() == 1){
				entity.setMsg("默认搜索已存在,操作失败!");
				entity.setSuccess(false);
				return entity;
			}
			engine.setCreateBy(SessionUtil.getLoginAdminName(request));
			engine.setUpdateBy(SessionUtil.getLoginAdminName(request));
		}else{
			if(defaultList.size() == 1 && engine.getStatus() == 1){
				int id = defaultList.get(0).getId();
				if(id != engine.getId()){
					entity.setMsg("默认搜索已存在,操作失败!");
					entity.setSuccess(false);
					return entity;
				}
			}
			engine.setUpdateBy(SessionUtil.getLoginAdminName(request));
		}

		try {
			engineService.saveEngineInfo(engine);
			entity.setMsg("succeed");
			entity.setSuccess(true);
		} catch (Exception ex) {
			LOGGER.error("--------- SearchEngineController.saveEngine error", ex);
			entity.setMsg("保存失败!");
			entity.setSuccess(false);
		}
		return entity;
	}

	/**
	 * 删除搜索引擎信息
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/clotho/engine/delEngine.do")
	public ExtStatusEntity delEngine(@RequestParam("id") int id,HttpServletRequest request){
		ExtStatusEntity entity = new ExtStatusEntity();
		try {
			int rel=engineService.delEngine(id);
			if(rel > 0){
				entity.setMsg("succeed");
				entity.setSuccess(true);
			}else{
				entity.setMsg("删除失败!");
				entity.setSuccess(false);
			}
		} catch (Exception ex) {
			LOGGER.error("--------- SearchEngineController.delEngine error", ex);
			entity.setMsg("删除失败!");
			entity.setSuccess(false);
		}
		LOGGER.info("delEngine");
		return entity;
	}

}
