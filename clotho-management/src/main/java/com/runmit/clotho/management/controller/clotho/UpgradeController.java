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
import com.runmit.clotho.core.domain.upgrade.UpgradePlan;
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
	
	/**
	 * version list
	 * @param start
	 * @param limit
	 * @param request
	 * @return
	 */
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
	
	/**
	 * save version
	 * @param version
	 * @param request
	 * @return
	 */
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
	
	/**
	 * delete version
	 * @param id
	 * @param request
	 * @return
	 */
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
	
	@RequestMapping(value = "/planlist.do")
	public @ResponseBody ExtEntity<UpgradePlan> getPlans(@RequestParam("version")String version,HttpServletRequest request) {
		ExtEntity<UpgradePlan> listdata = new ExtEntity<UpgradePlan>();
		
		List<UpgradePlan> list = versionService.getUpgradePlans(version);
		
		listdata.setRows(list);
		listdata.setResult(list.size());
		
		log.info("getPlans");
		return listdata;
	}
	
	@RequestMapping(value = "/delPlan.do")
	public @ResponseBody ExtStatusEntity delPlan(@RequestParam("id")int id,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		try{
			this.versionService.delPlan(id);
			
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			log.error("delPlan error",ex);
			entity.setMsg("删除失败");
			entity.setSuccess(false);
		}
		
		log.info("delPlan");
		return entity;
	}
	
	@RequestMapping(value = "/savePlan.do",method=RequestMethod.POST)
	public @ResponseBody ExtStatusEntity savePlan(UpgradePlan plan,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute(SecurityConstant.ADMIN_SESSION_ATTRIBUTE);
		if(plan.getId()==null||plan.getId()==0){
			plan.setCreateby(admin.getName());
		}else{
			plan.setUpdateby(admin.getName());
		}
		try{
			int result = this.versionService.savePlan(plan);
			if(result == -1){
				entity.setMsg("未找到From版本号");
				entity.setSuccess(false);
			}else if(result == -2){
				entity.setMsg("重复添加相同版本号升级计划");
				entity.setSuccess(false);
			}else{
				entity.setMsg("操作成功");
				entity.setSuccess(true);
			}
		}catch(Exception ex){
			log.error("saveVersion error",ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}
		
		log.info("saveVersion");
		return entity;
	}
}
