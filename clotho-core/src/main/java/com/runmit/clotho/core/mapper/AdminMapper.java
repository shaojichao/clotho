package com.runmit.uc.core.mapper;

import com.runmit.uc.core.domain.Admin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 *
 * @author TianLiang
 */
@CacheNamespace(implementation = org.mybatis.caches.memcached.MemcachedCache.class)
public interface AdminMapper {

    @Insert("INSERT INTO Admin (`username`,`password`,`realname`,`email`,`phone`,`qq`,`type`,`auth`,`status`,`loginIp`) "
            + "VALUES (#{username},#{password},#{realname},#{email},#{phone},#{qq},#{type},#{auth},#{status},#{loginIp})")
    @Options(flushCache=true, useGeneratedKeys = true, keyProperty = "id")
    void addUser(Admin adminEntity);

    @Update("UPDATE Admin SET username = #{username},password=#{password},realname=#{realname},"
            + "email=#{email},"
            + "phone=#{phone},"
            + "qq=#{qq},"
            + "type=#{type},"
            + "auth=#{auth},status=#{status},loginIp=#{loginIp} WHERE id=#{id}")
    int updateUser(Admin adminEntity);

    @Delete("DELETE FROM Admin WHERE id=#{id}")
    int deleteUser(Integer id);

    @Select("SELECT * FROM Admin WHERE id = #{id}")
    @Options(useCache=true, flushCache=false)
    Admin get(@Param("id") Integer id);
}
