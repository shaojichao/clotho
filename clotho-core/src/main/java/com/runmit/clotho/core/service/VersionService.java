package com.runmit.clotho.core.service;


import java.util.Date;
import java.util.List;

import com.runmit.clotho.core.domain.upgrade.UpgradePlan;
import com.runmit.clotho.core.domain.upgrade.Version;
import com.runmit.clotho.core.mapper.VersionMapper;
import com.runmit.clotho.core.util.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Scott.Xie
 */
@Service
@Transactional
public class VersionService {

    @Autowired
    private VersionMapper versionMapper;
    
    @Transactional(readOnly = true)
    public Version getLastestbyclientid( int clientid) {
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
    public Version getbyversion(String version) {
        return versionMapper.getbyversion(version);
    }

    @Transactional(readOnly = true)
//    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_'+#devicesn")
    public UpgradePlan getUpgradePlanbyversion(String version) {
        return versionMapper.getUpgradePlanbyversion(version);
    }
    
    @Transactional(readOnly = true)
	public List<UpgradePlan> getUpgradePlans(String version) {
    	return versionMapper.getUpgradePlans(version);
	}
    
    @Transactional(readOnly = true)
    public List<Version> getList(int start,int limit) {
        return versionMapper.getList(start, limit);
    }
    
    @Transactional(readOnly = true)
    public long getCount() {
        return versionMapper.getCount();
    }
    
    @Transactional(readOnly = false)
    public void saveVersion(Version version){
    	if(version.getId()==null||version.getId()==0){
    		version.setSerialno(DateUtils.getDateString(new Date(), "yyyyMMddHHmmss"));
    		this.versionMapper.addVersion(version);
    	}else{
    		this.versionMapper.updateVersion(version);
    	}
    }
    
    @Transactional(readOnly = false)
    public void delVersion(int id){
    	this.versionMapper.delVersion(id);
    }
    
    @Transactional(readOnly = false)
    public void delPlan(int id){
    	this.versionMapper.delPlan(id);
    }

    @Transactional(readOnly = false)
    public int savePlan(UpgradePlan plan){
    	Version version = this.versionMapper.getbyversion(plan.getVersion());
    	if(null==version){
    		return -1;
    	}
    	plan.setOriginid(version.getSerialno());
    	if(plan.getId()==null||plan.getId()==0){
    		UpgradePlan p = this.versionMapper.getUpgradePlan(plan.getOriginid(),plan.getUpgradeid());
    		if(p!=null){
    			return -2;
    		}
    		this.versionMapper.addPlan(plan);
    	}else{
    		this.versionMapper.updatePlan(plan);
    	}
    	return 0;
    }
}
