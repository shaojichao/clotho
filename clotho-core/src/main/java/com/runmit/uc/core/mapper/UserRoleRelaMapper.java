package com.runmit.uc.core.mapper;

import com.runmit.uc.core.domain.Admin;
import com.runmit.uc.core.domain.UserRoleRela;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 *
 * @author TianLiang
 */
@CacheNamespace(implementation = org.mybatis.caches.memcached.MemcachedCache.class)
public interface UserRoleRelaMapper {

    @Insert("INSERT INTO UserRoleRela (`id`,`roleid`) "
            + "VALUES (#{id},#{roleid})")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addUserRoleRela(UserRoleRela UserRoleRelaEntity);
//
//    @Update("UPDATE Admin SET username = #{username},password=#{password},realname=#{realname},"
//            + "email=#{email},"
//            + "phone=#{phone},"
//            + "qq=#{qq},"
//            + "type=#{type},"
//            + "auth=#{auth},status=#{status},loginIp=#{loginIp} WHERE id=#{id}")
//    @Options(flushCache = true)
//    int updateUser(Admin adminEntity);
//
//    @Delete("DELETE FROM Admin WHERE id=#{id}")
//    @Options(flushCache = true)
//    int deleteUser(Integer id);
//
    @Select("SELECT * FROM UserRoleRela WHERE id = #{id}")
    @Options(useCache = true, flushCache = false)
    UserRoleRela get(@Param("id") Integer id,@Param("roleid") Integer roleid);
}
