package com.runmit.uc.core.service;

import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.runmit.uc.core.domain.Admin;
import com.runmit.uc.core.mapper.AdminMapper;
import java.util.List;
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
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;
    
    @Transactional(readOnly = false)
    public int addUser(Admin adminEntity) {
        adminMapper.addUser(adminEntity);
        return adminEntity.getId();
    }

    @Transactional(readOnly = false)
    public int updateUser(Admin adminEntity) {
        return adminMapper.updateUser(adminEntity);
    }

    @Transactional(readOnly = false)
    public int deleteUser(Integer id) {
        return adminMapper.deleteUser(id);
    }

    @Transactional(readOnly = true)
    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_'+#id")
    public Admin getAdmin(Integer id) {
        System.out.println("fsfds");
        return adminMapper.get(id);
    }
    
}
