package com.runmit.clotho.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runmit.clotho.core.domain.drip.ActivationCode;
import com.runmit.clotho.core.mapper.ActivationCodeMapper;

/**
 * @author zhipeng.tian
 * 
 * @date 2015年11月9日
 */
@Service
@Transactional(readOnly = true)
public class ActivationCodeService {
	@Autowired
	private ActivationCodeMapper codeMapper;

	@Transactional(readOnly = false)
	public void addActivationCode(ActivationCode code) {
		this.codeMapper.addActivationCode(code);
	}

	public ActivationCode getActivationCode(String code) {
		return this.codeMapper.getActivationCode(code);
	}

	@Transactional(readOnly = false)
	public void update(String code) {
		this.codeMapper.update(code);
	}

	public long count() {
		return this.codeMapper.count();
	}

	public List<ActivationCode> getList(int start, int limit) {
		return this.codeMapper.getList(start, limit);
	}

	public List<ActivationCode> getListById(int id, int limit, int status) {
		return this.codeMapper.getListById(id, limit, status);
	}
}
