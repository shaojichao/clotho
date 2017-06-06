package com.runmit.clotho.management.controller.browser;

import com.runmit.clotho.core.domain.StatusEnum;
import com.runmit.clotho.core.domain.VersionConstant;
import com.runmit.clotho.core.domain.browser.InfoVersion;
import com.runmit.clotho.core.domain.browser.SearchEngine;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.browser.InfoVersionService;
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

/**
 * 搜索引擎Controller
 * @author lgz
 * @version 1.0
 * @date 2017-06-06
 */
@RestController
public class SearchEngineController{
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchEngineController.class);

	@Autowired
	private SearchEngineService engineService;
	@Autowired
	private InfoVersionService versionService;

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
		LOGGER.info("----------- SearchEngineController.getEngineList");
		return datas;
	}

	/**
	 * 添加或修改搜索引擎信息
	 * @param engine 搜索引擎信息对象
	 * @param request
	 * @return
	 */
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
		LOGGER.info("saveEngine");
		return entity;
	}

	/**
	 * 删除搜索引擎信息
	 * @param id 搜索引擎ID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/clotho/engine/delEngine.do")
	public ExtStatusEntity delEngine(@RequestParam("id") int id,HttpServletRequest request){
		ExtStatusEntity entity = new ExtStatusEntity();
		try{
			int rel=engineService.delEngine(id);
			if(rel > 0){
				entity.setMsg("succeed");
				entity.setSuccess(true);
			}else{
				entity.setMsg("删除失败!");
				entity.setSuccess(false);
			}
		}catch(Exception ex){
			LOGGER.error("--------- SearchEngineController.delEngine error", ex);
			entity.setMsg("删除失败!");
			entity.setSuccess(false);
		}
		LOGGER.info("delEngine");
		return entity;
	}

	/**
	 * 查询版本号
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/clotho/engine/getVersionNum.do")
	public ExtStatusEntity getVersionNum(HttpServletRequest request){
		ExtStatusEntity entity = new ExtStatusEntity();
		try{
			InfoVersion version = versionService.getVersionNum(VersionConstant.ENGINE_KEY);
			if(version != null){
				entity.setMsg(version.getVersionNo());
			}else{
				entity.setMsg("");
			}
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("--------- SearchEngineController.getVersionNum error", ex);
			entity.setMsg("获取版本号失败!");
			entity.setSuccess(false);
		}
		LOGGER.info("getVersionNum");
		return entity;
	}

	/**
	 * 生成版本号
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/clotho/engine/generateVersionNum.do")
	public ExtStatusEntity generateVersionNum(HttpServletRequest request){
		ExtStatusEntity entity = new ExtStatusEntity();
		try{
			String operator = SessionUtil.getLoginAdminName(request);
			String versionNum = versionService.generateVersionNum(VersionConstant.ENGINE_KEY, operator);
			entity.setMsg(versionNum);
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("--------- SearchEngineController.generateVersionNum error", ex);
			entity.setMsg("生成版本号失败!稍后重试!");
			entity.setSuccess(false);
		}
		LOGGER.info("generateVersionNum");
		return entity;
	}

}
