package com.runmit.clotho.management.controller.browser;

import com.runmit.clotho.core.domain.VersionConstant;
import com.runmit.clotho.core.domain.browser.InfoVersion;
import com.runmit.clotho.core.domain.browser.Navigation;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.browser.InfoVersionService;
import com.runmit.clotho.core.service.browser.NavigationService;
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
 * 导航栏Controller
 * @author lgz
 * @version 1.0
 * @date 2017-06-06
 */
@RestController
public class NavigationController{
	private static final Logger LOGGER = LoggerFactory.getLogger(NavigationController.class);

	@Autowired
	private NavigationService navService;
	@Autowired
	private InfoVersionService versionService;

	/**
	 * 导航栏列表查询
	 * @param name 导航栏名称
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/clotho/navigation/navigationList.do")
	public ExtEntity<Navigation> navigationList(
			@RequestParam(required = false) String name,
			@RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit){
		ExtEntity<Navigation> datas = new ExtEntity<Navigation>();

		List<Navigation> list = navService.getList(name,start, limit);
		datas.setRows(list);
		datas.setResult(navService.getCount(name));
		LOGGER.info("----------- NavigationController.navigationList");
		return datas;
	}

	/**
	 * 添加或修改导航栏信息
	 * @param nav 导航栏信息对象
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/clotho/navigation/saveNavigation.do", method = RequestMethod.POST)
	public ExtStatusEntity saveNavigation(Navigation nav,HttpServletRequest request){
		ExtStatusEntity entity = new ExtStatusEntity();
		if(nav.getId() == null){
			nav.setCreateBy(SessionUtil.getLoginAdminName(request));
			nav.setUpdateBy(SessionUtil.getLoginAdminName(request));
		}else{
			nav.setUpdateBy(SessionUtil.getLoginAdminName(request));
		}
		try {
			navService.saveNavigationInfo(nav);
			entity.setMsg("succeed");
			entity.setSuccess(true);
		} catch (Exception ex) {
			LOGGER.error("--------- NavigationController.saveNavigation error", ex);
			entity.setMsg("保存失败!");
			entity.setSuccess(false);
		}
		LOGGER.info("saveNavigation");
		return entity;
	}

	/**
	 * 删除导航栏信息
	 * @param id 导航栏ID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/clotho/navigation/delNavigation.do")
	public ExtStatusEntity delNavigation(@RequestParam("id") int id,HttpServletRequest request){
		ExtStatusEntity entity = new ExtStatusEntity();
		try{
			int rel=navService.delNavigation(id);
			if(rel > 0){
				entity.setMsg("succeed");
				entity.setSuccess(true);
			}else{
				entity.setMsg("删除失败!");
				entity.setSuccess(false);
			}
		}catch(Exception ex){
			LOGGER.error("--------- NavigationController.delNavigation error", ex);
			entity.setMsg("删除失败!");
			entity.setSuccess(false);
		}
		LOGGER.info("delNavigation");
		return entity;
	}

	/**
	 * 查询版本号
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/clotho/navigation/getVersionNum.do")
	public ExtStatusEntity getVersionNum(HttpServletRequest request){
		ExtStatusEntity entity = new ExtStatusEntity();
		try{
			InfoVersion version = versionService.getVersionNum(VersionConstant.NAVIGATION_KEY);
			if(version != null){
				entity.setMsg(version.getVersionNo());
			}else{
				entity.setMsg("");
			}
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("--------- NavigationController.getVersionNum error", ex);
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
	@RequestMapping(value = "/clotho/navigation/generateVersionNum.do")
	public ExtStatusEntity generateVersionNum(HttpServletRequest request){
		ExtStatusEntity entity = new ExtStatusEntity();
		try{
			String operator = SessionUtil.getLoginAdminName(request);
			String versionNum = versionService.generateVersionNum(VersionConstant.NAVIGATION_KEY, operator);
			entity.setMsg(versionNum);
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("--------- NavigationController.generateVersionNum error", ex);
			entity.setMsg("生成版本号失败!稍后重试!");
			entity.setSuccess(false);
		}
		LOGGER.info("generateVersionNum");
		return entity;
	}

}
