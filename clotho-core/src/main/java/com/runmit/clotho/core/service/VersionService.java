package com.runmit.clotho.core.service;

import java.util.Date;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;
import com.runmit.clotho.core.domain.upgrade.UpgradePlan;
import com.runmit.clotho.core.domain.upgrade.UpgradePlanMemo;
import com.runmit.clotho.core.domain.upgrade.Version;
import com.runmit.clotho.core.mapper.VersionMapper;
import com.runmit.clotho.core.util.DateUtils;
import com.runmit.clotho.log.domain.OpLog.OpType;
import com.runmit.clotho.log.service.OpLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 *
 * @author Scott.Xie
 */
@Service
@Transactional
@Component
public class VersionService {

	@Autowired
	private VersionMapper versionMapper;
	// @Autowired
	@Reference(version = "1.0.0")
	private OpLogService opLogService;

	@Transactional(readOnly = true)
	public Version getLastestbyclientid(int clientid) {
		return versionMapper.getLastestbyclientid(clientid);
	}

	@Transactional(readOnly = true)
	public Version getbyid(int id) {
		return versionMapper.getbyid(id);
	}

	@Transactional(readOnly = true)
	public Version getbyserialno(String serialno) {
		return versionMapper.getbyserialno(serialno);
	}

	@Transactional(readOnly = true)
	public Version getbyversion(String version, int clientId) {
		return versionMapper.getbyversion(version, clientId);
	}

    @Transactional(readOnly = true)
    public Version getOtaVersion(String brand, String model,String country,String hardwareVersion,String firmwareVersion) {
        return versionMapper.getOtaVersion(brand, model, country, hardwareVersion, firmwareVersion);
    }

	@Transactional(readOnly = true)
	public UpgradePlan getUpgradePlanbyversion(String version, int clientId) {
		return versionMapper.getUpgradePlanbyversion(version, clientId);
	}
	
	@Transactional(readOnly = true)
	public UpgradePlanMemo getUpgradePlanMemo(int planid, String lang) {
		return versionMapper.getMemoByLang(planid, lang);
	}

	@Transactional(readOnly = true)
	public List<UpgradePlan> getUpgradePlans(String version,int clientid) {
		return versionMapper.getUpgradePlans(version, clientid);
	}

	@Transactional(readOnly = true)
	public List<Version> getList(int start, int limit, int clientid) {
		return versionMapper.getList(start, limit, clientid);
	}

	@Transactional(readOnly = true)
	public long getCount(int clientid) {
		return versionMapper.getCount(clientid);
	}

	@Transactional(readOnly = false)
	public void saveVersion(Version version) {
		if (version.getId() == null || version.getId() == 0) {
			version.setSerialno(DateUtils.getDateString(new Date(),
					"yyyyMMddHHmmss"));
			this.versionMapper.addVersion(version);
			opLogService.saveObj(version, OpType.INSERT, "upgrade", "clotho",
					version.getCreateby());
		} else {
			Version temp = getbyid(version.getId());
			this.versionMapper.updateVersion(version);
			opLogService.updateObj(temp, version, OpType.UPDATE, "upgrade",
					"clotho", version.getUpdateby());
		}
	}

    @Transactional(readOnly = false)
    public void saveOtaVersion(Version version) {
        if (version.getId() == null || version.getId() == 0) {
            version.setSerialno(DateUtils.getDateString(new Date(),"yyyyMMddHHmmss"));
            this.versionMapper.addOtaVersion(version);
            opLogService.saveObj(version, OpType.INSERT, "upgrade", "clotho",
                    version.getCreateby());
        } else {
            Version temp = getbyid(version.getId());
            this.versionMapper.updateOtaVersion(version);
            opLogService.updateObj(temp, version, OpType.UPDATE, "upgrade",
                    "clotho", version.getUpdateby());
        }
    }

	@Transactional(readOnly = false)
	public void delVersion(int id, String adminName) {
		Version ver = this.getbyid(id);
		opLogService.saveObj(ver, OpType.DELETE, "upgrade",
				"clotho", adminName);
		String upgradeid = ver.getSerialno();
		this.versionMapper.delVersion(id);
		this.versionMapper.delPlanByUpgradeid(upgradeid);
	}

	@Transactional(readOnly = false)
	public void delPlan(int id, String adminName) {
		opLogService.saveObj(this.versionMapper.getUpgradePlabById(id),
				OpType.DELETE, "upgrade-plan", "clotho", adminName);
		this.versionMapper.delPlan(id);
	}

	@Transactional(readOnly = false)
	public int savePlan(UpgradePlan plan) {
		String versions = plan.getVersions();
		if(!StringUtils.isEmpty(versions)){
			String[] array = versions.split(",");
			StringBuilder sb = new StringBuilder();
			for(String str:array){
				Version version = this.versionMapper.getbyversion(str,
						plan.getClientid());
				if (null == version) {
					return -1;
				}
				sb.append(",").append(version.getSerialno());
			}
			sb.append(",");
			plan.setOriginid(sb.toString());
		}
		
		
		if (plan.getId() == null || plan.getId() == 0) {
			UpgradePlan p = this.versionMapper.getUpgradePlan(
					plan.getOriginid(), plan.getUpgradeid());
			if (p != null) {
				return -2;
			}
			this.versionMapper.addPlan(plan);
			opLogService.saveObj(plan, OpType.INSERT, "upgrade-plan", "clotho",
					plan.getCreateby());
		} else {
			UpgradePlan temp = this.versionMapper.getUpgradePlabById(plan
					.getId());
			this.versionMapper.updatePlan(plan);
			opLogService.updateObj(temp, plan, OpType.UPDATE, "upgrade-plan",
					"clotho", plan.getUpdateby());
		}
		return 0;
	}
	
	@Transactional(readOnly = false)
	public void delPlanMemo(int id, String adminName) {
		opLogService.saveObj(this.versionMapper.getUpgradePlabById(id),
				OpType.DELETE, "upgrade-plan-memo", "clotho", adminName);
		this.versionMapper.delPlanMemo(id);
	}
	
	@Transactional(readOnly = false)
	public int savePlanMemo(UpgradePlanMemo memo){
		if(memo.getId()==null||memo.getId()==0){
			if(this.versionMapper.getMemoCount(memo.getPlanid(), memo.getLanguage())>0){
				return -1;
			}
			this.versionMapper.savePlanMemo(memo);
			opLogService.saveObj(memo, OpType.INSERT, "upgrade-plan-memo", "clotho",
					memo.getCreateby());
		}else{
			UpgradePlanMemo temp = this.versionMapper.getMemo(memo.getId());
			this.versionMapper.updatePlanMemo(memo);
			opLogService.updateObj(temp, memo, OpType.UPDATE, "upgrade-plan-memo",
					"clotho", memo.getUpdateby());
		}
		return 0;
	}
	
	@Transactional(readOnly = true)
	public List<UpgradePlanMemo> getMemos(int planid){
		return this.versionMapper.getMemos(planid);
	}
}
