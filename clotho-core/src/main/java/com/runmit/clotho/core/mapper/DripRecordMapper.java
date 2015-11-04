package com.runmit.clotho.core.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.runmit.clotho.core.domain.drip.DripRecord;

/**
 *
 * @author zhipeng.tian
 */
@CacheNamespace(implementation = org.mybatis.caches.memcached.MemcachedCache.class)
public interface DripRecordMapper {

	@Insert("INSERT INTO DripRecord (`uid`,`acount`,`amount`,`code`) "
			+ "VALUES (#{uid},#{acount},#{amount},#{code})")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void addDripRecord(DripRecord record);

	@Select("SELECT * FROM DripRecord ORDER BY id DESC LIMIT #{start},#{limit}")
	List<DripRecord> getList(@Param("start") int start,
			@Param("limit") int limit);

	@Select("SELECT * FROM DripRecord where uid=#{uid} ORDER BY id DESC LIMIT 1")
	DripRecord getLast(@Param("uid") int uid);

	@Select("SELECT count(id) FROM DripRecord where uid=#{uid}"
			+ " AND createtime >= #{beginTime} "
			+ " AND createtime < #{endTime} ")
	long getCountByUid(@Param("uid") int uid,
			@Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime);

	@Select("SELECT sum(amount) FROM DripRecord where uid=#{uid}"
			+ " AND createtime >= #{beginTime} "
			+ " AND createtime < #{endTime} ")
	int getSumByUid(@Param("uid") int uid, @Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime);
}
