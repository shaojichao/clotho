package com.runmit.clotho.core.service;


import java.util.List;

import com.runmit.clotho.core.domain.userfeedback.UserFeedback;
import com.runmit.clotho.core.mapper.UserFeedbackMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Scott.Xie
 */
@Service
@Transactional
public class UserFeedbackService {

    @Autowired
    private UserFeedbackMapper userFeedbackMapper;
    
    @Transactional(readOnly = false)
    public int addUserFeedback(UserFeedback userFeedback) {
        userFeedbackMapper.addUserFeedback(userFeedback);
        return userFeedback.getId();
    }
    
    @Transactional(readOnly = true)
    public List<UserFeedback> getList(int clientId,int start,int limit){
    	return userFeedbackMapper.getList(clientId,start, limit);
    }
    
    @Transactional(readOnly = true)
    public long getCount(int clientId){
    	return userFeedbackMapper.getCount(clientId);
    }
}
