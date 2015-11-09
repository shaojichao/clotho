package com.runmit.clotho.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.runmit.clotho.core.domain.drip.ActivationCode;

/**
 * @author zhipeng.tian
 * 
 * @date 2015年11月9日
 */
public interface ActivationCodeMapper {
	@Insert("INSERT INTO ActivationCode (`code`,`status`,`dateEnd`,`createby`) "
			+ "VALUES (#{code},1,#{dateEnd},#{createby})")
	void addActivationCode(ActivationCode code);

	@Select("select * from ActivationCode where code=#{code}")
	ActivationCode getActivationCode(@Param("code") String code);

	@Update("update ActivationCode set status=0 where code=#{code}")
	void update(@Param("code") String code);

	@Select("select count(code) from ActivationCode")
	long count();

	@Select("select * from ActivationCode order by id desc limit #{start},#{limit}")
	List<ActivationCode> getList(@Param("start") int start,
			@Param("limit") int limit);
	
	@Select("<script>select * from ActivationCode where id>=#{id} "
			+ " <if test=\"status!=-1\"> and status=#{status}</if>"
			+ " limit #{limit}</script>")
	List<ActivationCode> getListById(@Param("id") int id,
			@Param("limit") int limit,@Param("status") int status);
	

}
