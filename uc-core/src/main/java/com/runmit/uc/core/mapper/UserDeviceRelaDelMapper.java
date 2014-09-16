package com.runmit.uc.core.mapper;

import com.runmit.uc.core.domain.UserDeviceRelaDel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *
 * @author TianLiang
 */
@CacheNamespace(implementation = org.mybatis.caches.memcached.MemcachedCache.class)
public interface UserDeviceRelaDelMapper {

    @Insert("INSERT INTO UserDeviceRelaDel (`id`,`devicesn`,`status`) "
            + "VALUES (#{id},#{devicesn},#{status})")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "delid")
    void addUserDeviceRelaDel(UserDeviceRelaDel UserDeviceRelaDelEntity);

    @Update("UPDATE UserDeviceRelaDel SET id = #{id},devicesn=#{devicesn},status=#{status} where delid=#{delid}")
    @Options(flushCache = true)
    void updateUserDeviceRelaDel(UserDeviceRelaDel UserDeviceRelaDelEntity);

    @Delete("DELETE FROM UserDeviceRelaDel WHERE id=#{id} and devicesn=#{devicesn}")
    @Options(flushCache = true)
    int deleteUserDeviceRelaDel(@Param("id") Integer id, @Param("devicesn") String devicesn);

    @Select("SELECT * FROM UserDeviceRelaDel WHERE  devicesn=#{devicesn}")
    @Options(useCache = true, flushCache = false)
    List<UserDeviceRelaDel> getRelaDelbyDevice(@Param("devicesn") String devicesn);

    @Select("SELECT * FROM UserDeviceRelaDel WHERE id = #{id} and devicesn=#{devicesn}")
    @Options(useCache = true, flushCache = false)
    List<UserDeviceRelaDel> get(@Param("id") Integer id, @Param("devicesn") String devicesn);

    @Select("SELECT * FROM UserDeviceRelaDel WHERE  id=#{id}")
    @Options(useCache = true, flushCache = false)
    List<UserDeviceRelaDel> getRelaDelbyId(@Param("id") Integer id);
}
