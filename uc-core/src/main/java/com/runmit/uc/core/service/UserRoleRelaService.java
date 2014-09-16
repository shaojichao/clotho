package com.runmit.uc.core.service;


import com.runmit.uc.core.domain.UserRoleRela;
import com.runmit.uc.core.mapper.UserRoleRelaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author TianLiang
 */
@Service
@Transactional
public class UserRoleRelaService {

    @Autowired
    private UserRoleRelaMapper userRoleRelaMapper;
    
    @Transactional(readOnly = false)
    public int addUserRoleRela(UserRoleRela UserRoleRelaEntity) {
        userRoleRelaMapper.addUserRoleRela(UserRoleRelaEntity);
        return UserRoleRelaEntity.getId();
    }

//    @Transactional(readOnly = false)
//    public int updateUser(Admin adminEntity) {
//        return adminMapper.updateUser(adminEntity);
//    }

//    @Transactional(readOnly = false)
//    public int deleteUser(Integer id) {
//        return adminMapper.deleteUser(id);
//    }
//
    @Transactional(readOnly = true)
    @Cacheable(value="defaultCache", key="#domain.UserRoleRela.id+'_'+#id+'_'+#roleid")
    public UserRoleRela getUserRoleRela(Integer id,Integer roleid) {
        return userRoleRelaMapper.get(id, roleid);
    }
}
