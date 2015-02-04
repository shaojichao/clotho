package com.runmit.clotho.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.runmit.clotho.core.domain.CountryCode;
import com.runmit.clotho.core.mapper.CountryCodeMapper;
import com.runmit.clotho.log.domain.OpLog.OpType;
import com.runmit.clotho.log.service.OpLogService;

/**
 * @author zhipeng.tian
 * 
 * @date 2015年2月2日
 */
@Service
@Transactional
public class CountryCodeService {
	@Autowired
    private CountryCodeMapper codeMapper;
    @Reference(version="1.0.0")
	private OpLogService opLogService;
    
    @Transactional(readOnly = true)
    public List<CountryCode> getList(){
    	return codeMapper.getList();
    }
    
    @Transactional(readOnly = false)
    public void save(CountryCode code){
    	if(code.getId()==null||code.getId()==0){
    		this.codeMapper.saveCountryCode(code);
    		this.opLogService.saveObj(code, OpType.INSERT, "countryCode", "clotho", code.getCreateby());
    	}else{
    		CountryCode temp = this.codeMapper.getCountryCode(code.getId());
    		this.codeMapper.updateCountryCode(code);
    		this.opLogService.updateObj(temp, code, OpType.UPDATE, "countryCode", "clotho", code.getUpdateby());
    	}
    }
    
    @Transactional(readOnly = false)
    public void delete(int id,String oper){
    	CountryCode temp = this.codeMapper.getCountryCode(id);
    	this.opLogService.saveObj(temp, OpType.DELETE, "countryCode", "clotho", oper);
    	this.codeMapper.delete(id);
    }
    
    @Transactional(readOnly = true)
    public List<CountryCode> getLanguages(){
    	return codeMapper.getLanguages();
    }
    
}
