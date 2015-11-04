package com.runmit.clotho.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runmit.clotho.core.domain.drip.DripRecord;
import com.runmit.clotho.core.mapper.DripRecordMapper;

/**
 *
 * @author zhipeng.tian
 */
@Service
@Transactional
public class DripService {

	@Autowired
	private DripRecordMapper dripMapper;

	@Transactional(readOnly = false)
	public void saveDripRecord(DripRecord record) {
		dripMapper.addDripRecord(record);
	}

	@Transactional(readOnly = true)
	public DripRecord getLast(int uid) {
		return this.dripMapper.getLast(uid);
	}

	@Transactional(readOnly = true)
	public List<DripRecord> getList(int start, int limit) {
		return this.dripMapper.getList(start, limit);
	}

	@Transactional(readOnly = true)
	public long getCountByUid(int uid, Date beginTime, Date endTime) {
		return dripMapper.getCountByUid(uid, beginTime, endTime);
	}

	@Transactional(readOnly = true)
	public long getSumByUid(int uid, Date beginTime, Date endTime) {
		return dripMapper.getSumByUid(uid, beginTime, endTime);
	}
}
