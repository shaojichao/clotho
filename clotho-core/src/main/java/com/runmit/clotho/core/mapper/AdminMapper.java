package com.runmit.clotho.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.runmit.clotho.core.domain.admin.AdminRole;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月29日
 */

public interface AdminMapper {
	@Select("SELECT * FROM AdminRole")
    List<AdminRole> getRoleList();
}
