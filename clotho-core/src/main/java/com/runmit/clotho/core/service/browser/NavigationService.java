package com.runmit.clotho.core.service.browser;

import com.runmit.clotho.core.domain.browser.Navigation;
import com.runmit.clotho.core.mapper.browser.NavigationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 导航栏Service
 * @author lgz
 * @version 1.0
 * @date 2017-06-06
 */
@Service
@Transactional
public class NavigationService{
	private static final Logger LOGGER = LoggerFactory.getLogger(NavigationService.class);

	@Autowired
	private NavigationMapper navMapper;

	/**
	 * 分页查找导航栏信息集合
	 * @param start
	 * @param limit
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Navigation> getList(String downloadUrl, String name, int start, int limit){
		List<Navigation> list = navMapper.getList(name,start, limit);
		for(Navigation nav: list){
			nav.setImgUploadUrl(downloadUrl);
			if(nav.getType() == 0){
				nav.setAppUrl(nav.getAppIdOrUrl());
			}else{
				nav.setAppId(nav.getAppIdOrUrl());
			}
		}
		return list;
	}

	/**
	 * 查找导航栏信息总条数
	 * @return
	 */
	@Transactional(readOnly = true)
	public long getCount(String name){
		return navMapper.getCounts(name);
	}

	/**
	 * 保存导航栏信息
	 * @param nav 导航栏信息对象
	 */
	@Transactional(readOnly = false)
	public void saveNavigationInfo(Navigation nav) throws Exception{
		if(nav.getType() == 0){
			nav.setAppIdOrUrl(nav.getAppUrl());
		}else{
			nav.setAppIdOrUrl(nav.getAppId());
		}
		if(nav.getId() == null){
			navMapper.addNavigation(nav);
			navMapper.updateSort(nav.getId());
		}else{
			navMapper.updateNavigation(nav);
		}
	}

	/**
	 * 根据ID删除导航栏信息
	 * @param id
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public int delNavigation(int id) throws Exception{
		return navMapper.deleteById(id);
	}

}
