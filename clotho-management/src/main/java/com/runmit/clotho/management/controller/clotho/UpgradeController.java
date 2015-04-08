package com.runmit.clotho.management.controller.clotho;

import java.io.BufferedReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.runmit.clotho.core.domain.picture.WeeklyPicture;
import com.runmit.clotho.core.service.WeeklyPictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.runmit.clotho.core.domain.upgrade.UpgradePlan;
import com.runmit.clotho.core.domain.upgrade.UpgradePlanMemo;
import com.runmit.clotho.core.domain.upgrade.Version;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.VersionService;
import com.runmit.clotho.management.security.SessionUtil;
import com.runmit.clotho.management.service.CDNService;

/**
 * @author zhipeng.tian
 * 
 *         2014年9月26日
 */
@Controller
@Component
@RequestMapping(value = "/clotho/upgrade")
public class UpgradeController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UpgradeController.class);

	@Autowired
	private VersionService versionService;
	@Autowired
	private CDNService cdnService;
    @Autowired
    private WeeklyPictureService weeklyPictureService;

	/**
	 * version list
	 * 
	 * @param start
	 * @param limit
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list.do")
	public @ResponseBody ExtEntity<Version> getVersions(
			@RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
			@RequestParam(value = "se_clientId", required = false, defaultValue = "1") Integer clientid,
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
	 * 
	 * @param version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveVersion.do", method = RequestMethod.POST)
	public @ResponseBody ExtStatusEntity saveVersion(
			Version version,
			@RequestParam(value = "size", required = false, defaultValue = "0") Long size,
			@RequestParam(value = "md5", required = false, defaultValue = "") String md5,
			HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();

		if (version.getId() == null || version.getId() == 0) {
			version.setCreateby(SessionUtil.getLoginAdminName(request));
		} else {
			version.setUpdateby(SessionUtil.getLoginAdminName(request));
		}
		try {
			if(size!=null&&size!=0){
				version.setFilesize(size);
			}
			this.versionService.saveVersion(version);
			
			//file dispatch
			LOGGER.info("size:{},md5:{}",size,md5);
			if(size!=null&&size!=0&&!StringUtils.isEmpty(md5)){
				int res = -1;
				int tryTime = 2;
				for(int i=0;i<tryTime;i++){
					res = this.cdnService.dispatchApp(version, md5, size);
					if(res==0){
						break;
					}
				}
				if(res!=0){
					entity.setMsg("cdn分发失败");
					entity.setSuccess(false);
					return entity;
				}
			}
			
			entity.setMsg("succeed");
			entity.setSuccess(true);
		} catch (Exception ex) {
			LOGGER.error("saveVersion error", ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}

		LOGGER.info("saveVersion");
		return entity;
	}

	/**
	 * delete version
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delVersion.do")
	public @ResponseBody ExtStatusEntity saveVersion(
			@RequestParam("id") int id, HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		try {
			this.versionService.delVersion(id,
					SessionUtil.getLoginAdminName(request));

			entity.setMsg("succeed");
			entity.setSuccess(true);
		} catch (Exception ex) {
			LOGGER.error("delVersion error", ex);
			entity.setMsg("删除失败");
			entity.setSuccess(false);
		}

		LOGGER.info("delVersion");
		return entity;

	}

	@RequestMapping(value = "/planlist.do")
	public @ResponseBody ExtEntity<UpgradePlan> getPlans(
			@RequestParam("version") String version,
			@RequestParam("clientid") int clientid, HttpServletRequest request) {
		ExtEntity<UpgradePlan> listdata = new ExtEntity<UpgradePlan>();

		List<UpgradePlan> list = versionService.getUpgradePlans(version,
				clientid);

		listdata.setRows(list);
		listdata.setResult(list.size());

		LOGGER.info("getPlans");
		return listdata;
	}

	@RequestMapping(value = "/delPlan.do")
	public @ResponseBody ExtStatusEntity delPlan(@RequestParam("id") int id,
			HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		try {
			this.versionService.delPlan(id,
					SessionUtil.getLoginAdminName(request));

			entity.setMsg("succeed");
			entity.setSuccess(true);
		} catch (Exception ex) {
			LOGGER.error("delPlan error", ex);
			entity.setMsg("删除失败");
			entity.setSuccess(false);
		}

		LOGGER.info("delPlan");
		return entity;
	}

	@RequestMapping(value = "/savePlan.do", method = RequestMethod.POST)
	public @ResponseBody ExtStatusEntity savePlan(UpgradePlan plan,
			HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();

		if (plan.getId() == null || plan.getId() == 0) {
			plan.setCreateby(SessionUtil.getLoginAdminName(request));
		} else {
			plan.setUpdateby(SessionUtil.getLoginAdminName(request));
		}
		try {
			int result = this.versionService.savePlan(plan);
			if (result == -1) {
				entity.setMsg("未找到From版本号");
				entity.setSuccess(false);
			} else if (result == -2) {
				entity.setMsg("重复添加相同版本号升级计划");
				entity.setSuccess(false);
			} else {
				entity.setMsg("操作成功");
				entity.setSuccess(true);
			}
		} catch (Exception ex) {
			LOGGER.error("savePlan error", ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}

		LOGGER.info("savePlan");
		return entity;
	}

	@RequestMapping(value = "/memolist.do")
	public @ResponseBody ExtEntity<UpgradePlanMemo> getMemos(
			@RequestParam("planid") int planid, HttpServletRequest request) {
		ExtEntity<UpgradePlanMemo> listdata = new ExtEntity<UpgradePlanMemo>();

		List<UpgradePlanMemo> list = versionService.getMemos(planid);

		listdata.setRows(list);
		listdata.setResult(list.size());

		LOGGER.info("getPlanMemos");
		return listdata;
	}

	@RequestMapping(value = "/delMemo.do")
	public @ResponseBody ExtStatusEntity delMemo(@RequestParam("id") int id,
			HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();
		try {
			this.versionService.delPlanMemo(id,
					SessionUtil.getLoginAdminName(request));

			entity.setMsg("succeed");
			entity.setSuccess(true);
		} catch (Exception ex) {
			LOGGER.error("delPlanMemo error", ex);
			entity.setMsg("删除失败");
			entity.setSuccess(false);
		}

		LOGGER.info("delPlanMemo");
		return entity;
	}

	@RequestMapping(value = "/saveMemo.do", method = RequestMethod.POST)
	public @ResponseBody ExtStatusEntity saveMemo(UpgradePlanMemo memo,
			HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();

		if (memo.getId() == null || memo.getId() == 0) {
			memo.setCreateby(SessionUtil.getLoginAdminName(request));
		} else {
			memo.setUpdateby(SessionUtil.getLoginAdminName(request));
		}
		try {
			int result = this.versionService.savePlanMemo(memo);
			if (result == -1) {
				entity.setMsg("该语言已经添加过");
				entity.setSuccess(false);
			} else {
				entity.setMsg("操作成功");
				entity.setSuccess(true);
			}
		} catch (Exception ex) {
			LOGGER.error("saveMemo error", ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}

		LOGGER.info("saveMemo");
		return entity;
	}
	
	@RequestMapping(value = "/cdnback.do", method = RequestMethod.POST)
	public @ResponseBody Object cdnback(HttpServletRequest request) {
		String line;
        StringBuilder data = new StringBuilder();
        try {
        	BufferedReader in = request.getReader();
            while ((line = in.readLine()) != null) {
                data.append(line);
            } 
            LOGGER.info(data.toString());
            JSONObject json = JSONObject.parseObject(data.toString());
            Integer status = json.getInteger("status");
    		String filename = json.getString("url");
            String taskId = json.getString("taskId");
            // 每周一图 标识符
            Integer taskTempId =Integer.valueOf(taskId.replace("weeklyPicture",""));

    		if(status!=0 && status!=5){
    			LOGGER.error("cdn dispatch response error",request.getParameter("desc"));
    		}else{
                // 每周一图CDN回调
                if (taskId.endsWith("weeklyPicture")){
                    WeeklyPicture weeklyPicture = this.weeklyPictureService.getPicture(taskTempId);
                    weeklyPicture.setUrl(filename);
                    weeklyPicture.setUrl(this.cdnService.getGSLBUrlPic(weeklyPicture));
                    this.weeklyPictureService.save(weeklyPicture);
                }else{
                    Version version = this.versionService.getbyid(taskTempId);
                    version.setPkgurl(filename);
                    version.setPkgurl(this.cdnService.getGSLBUrl(version));
                    this.versionService.saveVersion(version);
                }
    		}
        }catch (Exception e) {
        	LOGGER.error("cdn back read error",e);
        } 
		
		JSONObject json = new JSONObject();
		json.put("status", 0);
		json.put("desc", "成功");
		return json;
	}
	
}
