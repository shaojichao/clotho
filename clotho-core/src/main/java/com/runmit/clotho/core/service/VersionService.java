package com.runmit.clotho.core.service;


import java.util.List;

import com.runmit.clotho.core.domain.upgrade.UpgradePlan;
import com.runmit.clotho.core.domain.upgrade.Version;
import com.runmit.clotho.core.mapper.VersionMapper;

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
    		this.versionMapper.addVersion(version);
    	}else{
    		this.versionMapper.updateVersion(version);
    	}
    }

}
