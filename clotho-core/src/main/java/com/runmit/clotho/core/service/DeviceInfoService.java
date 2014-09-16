package com.runmit.clotho.core.service;


import com.runmit.clotho.core.domain.DeviceInfo;
import com.runmit.clotho.core.mapper.DeviceInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author TianLiang
 */
@Service
@Transactional
public class DeviceInfoService {

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;
    
    @Transactional(readOnly = false)
    public int addDeviceInfo(DeviceInfo DeviceInfoEntity) {
        deviceInfoMapper.addDeviceInfo(DeviceInfoEntity);
        return DeviceInfoEntity.getDeviceid();
    }

    @Transactional(readOnly = false)
    public void updateDeviceInfo(DeviceInfo DeviceInfoEntity) {
        deviceInfoMapper.updateDeviceInfo(DeviceInfoEntity);
    }

    @Transactional(readOnly = false)
    public void addDeviceRegUser(String devicesn) {
        deviceInfoMapper.addDeviceRegUser(devicesn);
    }

    @Transactional(readOnly = false)
    public void deleteDeviceInfo(Integer deviceid) {
        deviceInfoMapper.deleteDeviceInfo(deviceid);
    }
//
//    @Transactional(readOnly = true)
//    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_'+#id+'_'+#devicesn")
//    public UserDeviceRela getUserDeviceRela(Integer id,String devicesn) {
//        return userDeviceRelaMapper.get(id, devicesn);
//    }

    @Transactional(readOnly = true)
    //    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_'+#devicesn")
    public DeviceInfo getbyid(int deviceid) {
    return deviceInfoMapper.getbyid(deviceid);
}

    @Transactional(readOnly = true)
//    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_'+#devicesn")
    public DeviceInfo getbysn(String devicesn) {
        return deviceInfoMapper.getbysn(devicesn);
    }

    @Transactional(readOnly = true)
    public List<DeviceInfo> getAllDeviceInfo() {
        return deviceInfoMapper.getAll();
    }

//    @Transactional(readOnly = true)
//    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_'+#id")
//    public List<UserDeviceRela> getRelabyId(Integer id) {
//        return userDeviceRelaMapper.getRelabyId(id);
//    }
}
