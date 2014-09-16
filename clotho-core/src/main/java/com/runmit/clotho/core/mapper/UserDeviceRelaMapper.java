package com.runmit.uc.core.mapper;

import com.runmit.uc.core.domain.UserDeviceRela;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *
 * @author TianLiang
 */
@CacheNamespace(implementation = org.mybatis.caches.memcached.MemcachedCache.class)
public interface UserDeviceRelaMapper {

    @Insert("INSERT INTO UserDeviceRela (`id`,`devicesn`,`devicetype`,`token`) "
            + "VALUES (#{id},#{devicesn},#{devicetype},#{token})")
    @Options(flushCache = true)
    void addUserDeviceRela(UserDeviceRela UserDeviceRelaEntity);

    @Update("UPDATE UserDeviceRela SET id = #{id},devicesn=#{devicesn},devicetype=#{devicetype},token=#{token} where id=#{id} and devicesn=#{devicesn}")
    @Options(flushCache = true)
    void updateUserDeviceRela(UserDeviceRela UserDeviceRelaEntity);

    @Delete("DELETE FROM UserDeviceRela WHERE id=#{id} and devicesn=#{devicesn}")
    @Options(flushCache = true)
    void deleteUserDeviceRela(@Param("id") Integer id, @Param("devicesn") String devicesn);

    @Select("SELECT * FROM UserDeviceRela WHERE  devicesn=#{devicesn}")
    @Options(useCache = true, flushCache = false)
    List<UserDeviceRela> getRelabyDevice(@Param("devicesn") String devicesn);

    @Select("SELECT * FROM UserDeviceRela WHERE id = #{id} and devicesn=#{devicesn}")
    @Options(useCache = true, flushCache = false)
    UserDeviceRela get(@Param("id") Integer id, @Param("devicesn") String devicesn);

    @Select("SELECT * FROM UserDeviceRela WHERE id = #{id} and token is not null and token!=''")
    @Options(useCache = true, flushCache = false)
    List<UserDeviceRela> getLoginbyId(@Param("id") Integer id);

    @Select("SELECT * FROM UserDeviceRela WHERE  id=#{id}")
    @Options(useCache = true, flushCache = false)
    List<UserDeviceRela> getRelabyId(@Param("id") Integer id);
}
