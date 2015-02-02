package com.runmit.clotho.management.controller.clotho;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runmit.clotho.core.domain.CountryCode;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.CountryCodeService;
import com.runmit.clotho.management.security.SessionUtil;

/**
 * @author zhipeng.tian
 * 
 * @date 2015年2月2日
 */
@Controller
@RequestMapping(value = "/clotho/countryCode")
public class CountryCodeController {
	private static final Logger LOGGER = LoggerFactory
            .getLogger(CountryCodeController.class);
	
	@Autowired
	private CountryCodeService codeService;
	
	@RequestMapping(value = "/allList.do")
	public @ResponseBody ExtEntity<CountryCode> getCountryCodes() {
		
		List<CountryCode> list = this.codeService.getList();
		ExtEntity<CountryCode> entity = new ExtEntity<CountryCode>();
		entity.setResult(list.size());
		entity.setRows(list);
		return entity;
	}
	
	@RequestMapping(value = "/save.do")
	public @ResponseBody ExtStatusEntity saveCountryCode(CountryCode code,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		
		if(code.getId()==null||code.getId()==0){
			code.setCreateby(SessionUtil.getLoginAdminName(request));
		}else{
			code.setUpdateby(SessionUtil.getLoginAdminName(request));
		}
		try{
			this.codeService.save(code);
			
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("saveCountryCode error",ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}
		
		LOGGER.info("saveCountryCode");
		return entity;
	}
	
	@RequestMapping(value = "/delete.do")
	public @ResponseBody ExtStatusEntity delete(@RequestParam("id")Integer id,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		try{
			this.codeService.delete(id, SessionUtil.getLoginAdminName(request));
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("delete country code error",ex);
			entity.setMsg("删除失败");
			entity.setSuccess(false);
		}
		return entity;
	}
}
