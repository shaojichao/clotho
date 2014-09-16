package com.runmit.clotho.core.service;


import com.runmit.clotho.core.domain.Upgrade;
import com.runmit.clotho.core.domain.UpgradeDependence;
import com.runmit.clotho.core.mapper.UpgradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Scott.Xie
 */
@Service
@Transactional
public class UpgradeService {

    @Autowired
    private UpgradeMapper upgradeMapper;
    
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
    public Upgrade getbyid(int id) {
    return upgradeMapper.getbyid(id);
}

    @Transactional(readOnly = true)
//    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_'+#devicesn")
    public UpgradeDependence getdependencebyorigin(int originid) {
        return upgradeMapper.getdependencebyorigin(originid);
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
