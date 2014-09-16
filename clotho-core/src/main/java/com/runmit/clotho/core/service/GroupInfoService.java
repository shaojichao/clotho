package com.runmit.uc.core.service;

import com.runmit.uc.core.domain.Admin;
import com.runmit.uc.core.domain.GroupInfo;
import com.runmit.uc.core.mapper.AdminMapper;
import com.runmit.uc.core.mapper.GroupInfoMapper;
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
public class GroupInfoService {

    @Autowired
    private GroupInfoMapper groupInfoMapper;
    
    @Transactional(readOnly = false)
    public int addGroup(GroupInfo groupInfoEntity) {
        groupInfoMapper.addGroup(groupInfoEntity);
        return groupInfoEntity.getGroupid();
    }

    @Transactional(readOnly = false)
    public int updateGroup(GroupInfo groupInfoEntity) {
        return groupInfoMapper.updateGroup(groupInfoEntity);
    }

    @Transactional(readOnly = false)
    public int deleteGroup(Integer id) {
        return groupInfoMapper.deleteGroup(id);
    }

    @Transactional(readOnly = true)
    @Cacheable(value="defaultCache", key="#root.targetClass.simpleName+'_'+#groupid")
    public GroupInfo getGroupInfo(Integer groupid) {
        return groupInfoMapper.get(groupid);
    }
    
}
