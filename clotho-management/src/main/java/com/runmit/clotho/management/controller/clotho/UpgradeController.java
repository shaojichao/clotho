package com.runmit.clotho.management.controller.clotho;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runmit.clotho.core.domain.admin.Admin;
import com.runmit.clotho.core.domain.upgrade.Version;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.VersionService;
import com.runmit.clotho.management.security.SecurityConstant;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月26日
 */
@Controller
@Component
@RequestMapping(value = "/clotho/upgrade")
public class UpgradeController {
	private static final Logger log = LoggerFactory
            .getLogger(UpgradeController.class);
	
	@Autowired
    private VersionService versionService;
	
	@RequestMapping(value = "/list.do")
	public @ResponseBody ExtEntity<Version> getVersions(
			@RequestParam(value = "start", required = false,defaultValue="0") Integer start,
            @RequestParam(value = "limit", required = false,defaultValue="20") Integer limit,
            HttpServletRequest request) {
		ExtEntity<Version> listdata = new ExtEntity<Version>();
		
		List<Version> list = versionService.getList(start, limit);
		
		listdata.setRows(list);
		listdata.setResult(versionService.getCount());
		
		log.info("getVersions");
		return listdata;
	}
	
	@RequestMapping(value = "/saveVersion.do",method=RequestMethod.POST)
	public @ResponseBody ExtStatusEntity saveVersion(Version version,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute(SecurityConstant.ADMIN_SESSION_ATTRIBUTE);
		if(version.getId()==null||version.getId()==0){
			version.setCreateby(admin.getName());
		}else{
			version.setUpdateby(admin.getName());
		}
		try{
			this.versionService.saveVersion(version);
			
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			log.error("saveVersion error",ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}
		
		log.info("saveVersion");
		return entity;
	}
	
	@RequestMapping(value = "/delVersion.do")
	public @ResponseBody ExtStatusEntity saveVersion(@RequestParam("id")int id,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		try{
			this.versionService.delVersion(id);
			
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			log.error("delVersion error",ex);
			entity.setMsg("删除失败");
			entity.setSuccess(false);
		}
		
		log.info("delVersion");
		return entity;
		
	}
}
