package com.runmit.clotho.core.service.browser;

import com.runmit.clotho.core.domain.browser.InfoVersion;
import com.runmit.clotho.core.mapper.browser.InfoVersionMapper;
import com.runmit.clotho.core.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 版本信息Service
 * @author lgz
 * @version 1.0
 * @date 2017-06-05
 */
@Service
@Transactional
public class InfoVersionService{
	private static final Logger LOGGER = LoggerFactory.getLogger(InfoVersionService.class);

	@Autowired
	private InfoVersionMapper versionMapper;

	/**
	 * 根据版本key查找版本信息
	 * @param versionKey
	 * @return
	 */
	@Transactional(readOnly = true)
	public InfoVersion getVersionNum(String versionKey){
		return versionMapper.selectByVersionKey(versionKey);
	}

	/**
	 * 生成版本号
	 * @param versionKey 版本key
	 * @param operator 当前操作员
	 * @return versionNum 返回新生成的版本号
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public String generateVersionNum(String versionKey,String operator) throws Exception{
		String versionNum= DateUtils.generateVersionNum();
		InfoVersion version=new InfoVersion();
		version.setVersionNo(versionNum);
		version.setUpdateBy(operator);

		//重新生成版本号之前检查当前版本key是否存在，不存在新增，存在直接更新
		InfoVersion po = versionMapper.selectByVersionKey(versionKey);
		if(po == null){
			version.setVersionKey(versionKey);
			version.setCreateBy(operator);
			versionMapper.addInfoVersion(version);
		}else{
			version.setId(po.getId());
			versionMapper.updateInfoVersion(version);
		}
		return versionNum;
	}

}
