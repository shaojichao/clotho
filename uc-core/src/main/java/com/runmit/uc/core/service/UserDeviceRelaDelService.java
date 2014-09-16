package com.runmit.uc.core.service;


import com.runmit.uc.core.domain.UserDeviceRelaDel;
import com.runmit.uc.core.mapper.UserDeviceRelaDelMapper;
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
public class UserDeviceRelaDelService {

    @Autowired
    private UserDeviceRelaDelMapper userDeviceRelaDelMapper;
    
    @Transactional(readOnly = false)
    @Caching(evict = {
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_'+#UserDeviceRelaDelEntity.id+'_'+#UserDeviceRelaDelEntity.devicesn"),
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_devicesn_'+#UserDeviceRelaDelEntity.devicesn"),
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_listid_'+#UserDeviceRelaDelEntity.id")
    })
    public int addUserDeviceRelaDel(UserDeviceRelaDel UserDeviceRelaDelEntity) {
        userDeviceRelaDelMapper.addUserDeviceRelaDel(UserDeviceRelaDelEntity);
        return UserDeviceRelaDelEntity.getDelid();
    }

    @Transactional(readOnly = false)
    @Caching(evict = {
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_'+#UserDeviceRelaDelEntity.id+'_'+#UserDeviceRelaDelEntity.devicesn"),
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_devicesn_'+#UserDeviceRelaDelEntity.devicesn"),
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_listid_'+#UserDeviceRelaDelEntity.id")
    })
    public void updateUserDeviceRelaDel(UserDeviceRelaDel UserDeviceRelaDelEntity) {
        userDeviceRelaDelMapper.updateUserDeviceRelaDel(UserDeviceRelaDelEntity);
    }

    @Transactional(readOnly = false)
    @Caching(evict = {
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_'+#id+'_'+#devicesn"),
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_devicesn_'+#devicesn"),
            @CacheEvict(value = "defaultCache", key = "#root.targetClass.simpleName+'_listid_'+#id")
    })
    public int deleteUserDeviceRelaDel(Integer id,String devicesn) {
        return userDeviceRelaDelMapper.deleteUserDeviceRelaDel(id, devicesn);
    }
//
    @Transactional(readOnly = true)
    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_'+#id+'_'+#devicesn",unless = "#result.size() == 0")//TODO:空的时候表达式咋写呢?
    public List<UserDeviceRelaDel> getUserDeviceRelaDel(Integer id,String devicesn) {
        return userDeviceRelaDelMapper.get(id, devicesn);
    }

    @Transactional(readOnly = true)
    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_devicesn_'+#devicesn",unless = "#result.size() == 0")
    public List<UserDeviceRelaDel> getRelaDelbyDevice(String devicesn) {
        return userDeviceRelaDelMapper.getRelaDelbyDevice(devicesn);
    }

    @Transactional(readOnly = true)
    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_listid_'+#id",unless = "#result.size() == 0")
    public List<UserDeviceRelaDel> getRelaDelbyId(Integer id) {
        return userDeviceRelaDelMapper.getRelaDelbyId(id);
    }

}
