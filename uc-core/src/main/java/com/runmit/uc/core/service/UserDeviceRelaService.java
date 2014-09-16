package com.runmit.uc.core.service;


import com.runmit.uc.core.domain.UserDeviceRela;
import com.runmit.uc.core.mapper.UserDeviceRelaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author TianLiang
 */
@Service
@Transactional
public class UserDeviceRelaService {

    @Autowired
    private UserDeviceRelaMapper userDeviceRelaMapper;
    
    @Transactional(readOnly = false)
    @Caching(evict = {
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_'+#UserDeviceRelaEntity.id+'_'+#UserDeviceRelaEntity.devicesn"),
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_devicesn_'+#UserDeviceRelaEntity.devicesn"),
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_listid_'+#UserDeviceRelaEntity.id"),
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_idlogin_'+#UserDeviceRelaEntity.id")
    })
    public void addUserDeviceRela(UserDeviceRela UserDeviceRelaEntity) {
        userDeviceRelaMapper.addUserDeviceRela(UserDeviceRelaEntity);
    }

    @Transactional(readOnly = false)
    @Caching(evict = {
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_'+#UserDeviceRelaEntity.id+'_'+#UserDeviceRelaEntity.devicesn"),
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_devicesn_'+#UserDeviceRelaEntity.devicesn"),
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_listid_'+#UserDeviceRelaEntity.id"),
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_idlogin_'+#UserDeviceRelaEntity.id")
    })
    public void updateUserDeviceRela(UserDeviceRela UserDeviceRelaEntity) {
        userDeviceRelaMapper.updateUserDeviceRela(UserDeviceRelaEntity);
    }

    @Transactional(readOnly = false)
    @Caching(evict = {
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_'+#id+'_'+#devicesn"),
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_devicesn_'+#devicesn"),
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_listid_'+#id"),
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_idlogin_'+#id")
    })
    public void deleteUserDeviceRela(Integer id,String devicesn) {
        userDeviceRelaMapper.deleteUserDeviceRela(id, devicesn);
    }
//
    @Transactional(readOnly = true)
    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_'+#id+'_'+#devicesn",unless = "#result == null")
    public UserDeviceRela getUserDeviceRela(Integer id,String devicesn) {
        return userDeviceRelaMapper.get(id, devicesn);
    }

    @Transactional(readOnly = true)
    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_devicesn_'+#devicesn",unless = "#result == null")
    public List<UserDeviceRela> getRelabyDevice(String devicesn) {
        return userDeviceRelaMapper.getRelabyDevice(devicesn);
    }

    @Transactional(readOnly = true)
    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_listid_'+#id",unless = "#result == null")
    public List<UserDeviceRela> getRelabyId(Integer id) {
        return userDeviceRelaMapper.getRelabyId(id);
    }

    @Transactional(readOnly = true)
    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_idlogin_'+#id",unless = "#result == null")
    public List<UserDeviceRela> getLoginbyId(Integer id) {
        return userDeviceRelaMapper.getLoginbyId(id);
    }
}
