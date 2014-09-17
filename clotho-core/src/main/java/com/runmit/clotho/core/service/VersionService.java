package com.runmit.clotho.core.service;


import com.runmit.clotho.core.domain.Version;
import com.runmit.clotho.core.domain.UpgradePlan;
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
    
//    @Transactional(readOnly = false)
//    public int addDeviceInfo(DeviceInfo DeviceInfoEntity) {
//        deviceInfoMapper.addDeviceInfo(DeviceInfoEntity);
//        return DeviceInfoEntity.getDeviceid();
//    }
//
//    @Transactional(readOnly = false)
//    public void updateDeviceInfo(DeviceInfo DeviceInfoEntity) {
//        deviceInfoMapper.updateDeviceInfo(DeviceInfoEntity);
//    }
//
//    @Transactional(readOnly = false)
//    public void addDeviceRegUser(String devicesn) {
//        deviceInfoMapper.addDeviceRegUser(devicesn);
//    }
//
//    @Transactional(readOnly = false)
//    public void deleteDeviceInfo(Integer deviceid) {
//        deviceInfoMapper.deleteDeviceInfo(deviceid);
//    }
//
//    @Transactional(readOnly = true)
//    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_'+#id+'_'+#devicesn")
//    public UserDeviceRela getUserDeviceRela(Integer id,String devicesn) {
//        return userDeviceRelaMapper.get(id, devicesn);
//    }

    @Transactional(readOnly = true)
//    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_'+#devicesn")
    public Version getLastestbyclientid( int clientid) {
        return versionMapper.getLastestbyclientid(clientid);
    }

    @Transactional(readOnly = true)
    //    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_'+#devicesn")
    public Version getbyid(int id) {
    return versionMapper.getbyid(id);
}

    @Transactional(readOnly = true)
//    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_'+#devicesn")
    public Version getbyserialno(String serialno) {
        return versionMapper.getbyserialno(serialno);
    }

    @Transactional(readOnly = true)
//    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_'+#devicesn")
    public Version getbyversion(String version) {
        return versionMapper.getbyversion(version);
    }

    @Transactional(readOnly = true)
//    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_'+#devicesn")
    public UpgradePlan getUpgradePlanbyversion(String version) {
        return versionMapper.getUpgradePlanbyversion(version);
    }

//    @Transactional(readOnly = true)
//    public List<DeviceInfo> getAllDeviceInfo() {
//        return deviceInfoMapper.getAll();
//    }

//    @Transactional(readOnly = true)
//    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_'+#id")
//    public List<UserDeviceRela> getRelabyId(Integer id) {
//        return userDeviceRelaMapper.getRelabyId(id);
//    }
}
