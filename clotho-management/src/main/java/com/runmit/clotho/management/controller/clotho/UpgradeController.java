package com.runmit.clotho.management.controller.clotho;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runmit.clotho.core.domain.upgrade.UpgradePlan;
import com.runmit.clotho.core.domain.upgrade.UpgradePlanMemo;
import com.runmit.clotho.core.domain.upgrade.Version;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.VersionService;
import com.runmit.clotho.management.security.SessionUtil;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月26日
 */
@Controller
@Component
@RequestMapping(value = "/clotho/upgrade")
public class UpgradeController {
	private static final Logger LOGGER = LoggerFactory
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
            @RequestParam(value = "se_clientId", required = false,defaultValue="1") Integer clientid,
            HttpServletRequest request) {
		ExtEntity<Version> listdata = new ExtEntity<Version>();
		
		List<Version> list = versionService.getList(start, limit, clientid);
		
		listdata.setRows(list);
		listdata.setResult(versionService.getCount(clientid));
		
		LOGGER.info("getVersions");
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
		
		if(version.getId()==null||version.getId()==0){
			version.setCreateby(SessionUtil.getLoginAdminName(request));
		}else{
			version.setUpdateby(SessionUtil.getLoginAdminName(request));
		}
		try{
			this.versionService.saveVersion(version);
			
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("saveVersion error",ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}
		
		LOGGER.info("saveVersion");
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
			this.versionService.delVersion(id,SessionUtil.getLoginAdminName(request));
			
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("delVersion error",ex);
			entity.setMsg("删除失败");
			entity.setSuccess(false);
		}
		
		LOGGER.info("delVersion");
		return entity;
		
	}
	
	@RequestMapping(value = "/planlist.do")
	public @ResponseBody ExtEntity<UpgradePlan> getPlans(@RequestParam("version")String version,@RequestParam("clientid")int clientid,HttpServletRequest request) {
		ExtEntity<UpgradePlan> listdata = new ExtEntity<UpgradePlan>();
		
		List<UpgradePlan> list = versionService.getUpgradePlans(version,clientid);
		
		listdata.setRows(list);
		listdata.setResult(list.size());
		
		LOGGER.info("getPlans");
		return listdata;
	}
	
	@RequestMapping(value = "/delPlan.do")
	public @ResponseBody ExtStatusEntity delPlan(@RequestParam("id")int id,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		try{
			this.versionService.delPlan(id,SessionUtil.getLoginAdminName(request));
			
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("delPlan error",ex);
			entity.setMsg("删除失败");
			entity.setSuccess(false);
		}
		
		LOGGER.info("delPlan");
		return entity;
	}
	
	@RequestMapping(value = "/savePlan.do",method=RequestMethod.POST)
	public @ResponseBody ExtStatusEntity savePlan(UpgradePlan plan,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		
		if(plan.getId()==null||plan.getId()==0){
			plan.setCreateby(SessionUtil.getLoginAdminName(request));
		}else{
			plan.setUpdateby(SessionUtil.getLoginAdminName(request));
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
			LOGGER.error("savePlan error",ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}
		
		LOGGER.info("savePlan");
		return entity;
	}
	
	@RequestMapping(value = "/memolist.do")
	public @ResponseBody ExtEntity<UpgradePlanMemo> getMemos(@RequestParam("planid")int planid,HttpServletRequest request) {
		ExtEntity<UpgradePlanMemo> listdata = new ExtEntity<UpgradePlanMemo>();
		
		List<UpgradePlanMemo> list = versionService.getMemos(planid);
		
		listdata.setRows(list);
		listdata.setResult(list.size());
		
		LOGGER.info("getPlanMemos");
		return listdata;
	}
	
	@RequestMapping(value = "/delMemo.do")
	public @ResponseBody ExtStatusEntity delMemo(@RequestParam("id")int id,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		try{
			this.versionService.delPlanMemo(id,SessionUtil.getLoginAdminName(request));
			
			entity.setMsg("succeed");
			entity.setSuccess(true);
		}catch(Exception ex){
			LOGGER.error("delPlanMemo error",ex);
			entity.setMsg("删除失败");
			entity.setSuccess(false);
		}
		
		LOGGER.info("delPlanMemo");
		return entity;
	}
	
	@RequestMapping(value = "/saveMemo.do",method=RequestMethod.POST)
	public @ResponseBody ExtStatusEntity saveMemo(UpgradePlanMemo memo,HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		
		if(memo.getId()==null||memo.getId()==0){
			memo.setCreateby(SessionUtil.getLoginAdminName(request));
		}else{
			memo.setUpdateby(SessionUtil.getLoginAdminName(request));
		}
		try{
			int result = this.versionService.savePlanMemo(memo);
			if(result == -1){
				entity.setMsg("该语言已经添加过");
				entity.setSuccess(false);
			}else{
				entity.setMsg("操作成功");
				entity.setSuccess(true);
			}
		}catch(Exception ex){
			LOGGER.error("saveMemo error",ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}
		
		LOGGER.info("saveMemo");
		return entity;
	}
}
