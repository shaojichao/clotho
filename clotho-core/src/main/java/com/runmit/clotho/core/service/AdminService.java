package com.runmit.clotho.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runmit.clotho.core.mapper.AdminMapper;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月29日
 */
@Service
@Transactional
public class AdminService {
	@Autowired
	private AdminMapper adminMapper;
}
