package com.runmit.uc.core.service;


import com.runmit.uc.core.domain.UserInfo;
import com.runmit.uc.core.domain.UserRoleRela;
import com.runmit.uc.core.mapper.UserInfoMapper;
import com.runmit.uc.core.mapper.UserRoleRelaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author TianLiang
 */
@Service
@Transactional
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    
    @Transactional(readOnly = false)
    public int addUserInfo(UserInfo UserInfoEntity) {
        userInfoMapper.addUserInfo(UserInfoEntity);
        return UserInfoEntity.getId();
    }

    @Transactional(readOnly = false)
    @CacheEvict(value = "defaultCache",key = "#root.targetClass.simpleName+'_'+#UserInfoEntity.id")
    public int updateUser(UserInfo UserInfoEntity) {
        return userInfoMapper.updateUser(UserInfoEntity);
    }

    @Transactional(readOnly = false)
    @CacheEvict(value = "defaultCache",key = "#root.targetClass.simpleName+'_'+#id")
    public void deleteUser(Integer id) {
        userInfoMapper.deleteUser(id);
    }
//
    @Transactional(readOnly = true)
    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_'+#id")
    public UserInfo getUserInfo(Integer id) {
        return userInfoMapper.get(id);
    }

    @Transactional(readOnly = true)
//    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_'+#userid")
    public UserInfo getUserInfo(String userid) {
        return userInfoMapper.getbyUserId(userid);
    }

    @Transactional(readOnly = true)
    public List<UserInfo> getAllUserInfo() {
        return userInfoMapper.getAll();
    }

    @Transactional(readOnly = true)
//    @Cacheable(value="defaultCache" , key="#root.targetClass.simpleName+'_mobile_'+#mobile")
    public List<UserInfo> getbyMobile(String mobile) {
        return userInfoMapper.getbyMobile(mobile);
    }

    @Transactional(readOnly = true)
//TODO:不能模糊匹配Evict的key,所以这种非主键id的key没法匹配进行Evict,有方案么...
//    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_mail_'+#mobile")
    public List<UserInfo> getbyMail(String mail) {
        return userInfoMapper.getbyMail(mail);
    }
}
