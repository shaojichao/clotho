package com.runmit.clotho.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.runmit.clotho.core.domain.CountryCode;

/**
 * @author zhipeng.tian
 * 
 * @date 2015年2月2日
 */
public interface CountryCodeMapper {
	@Insert("INSERT INTO CountryCode (`name`,`countrycode`,`locale`,`language`,`createby`) "
			+ "values (#{name},#{countrycode},#{locale},#{language},#{createby})")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void saveCountryCode(CountryCode countryCode);
	
	@Update("update CountryCode set name=#{name},countrycode=#{countrycode},language=#{language},locale=#{locale},updateby=#{updateby},updatetime=now() where id=#{id}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void updateCountryCode(CountryCode countryCode);
	
	@Select("select * from CountryCode")
	List<CountryCode> getList();
	
	@Select("select * from CountryCode where id=#{id}")
	CountryCode getCountryCode(@Param("id") int id);
	
	@Delete("delete from CountryCode where id=#{id}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void delete(@Param("id") int id);
}
