package com.runmit.uc.core.mapper;

import com.runmit.uc.core.domain.GroupInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *
 * @author TianLiang
 */
@CacheNamespace(implementation = org.mybatis.caches.memcached.MemcachedCache.class)
public interface GroupInfoMapper {

    @Insert("INSERT INTO GroupInfo (`username`,`password`,`realname`,`email`,`phone`,`qq`,`type`,`auth`,`status`,`loginIp`) "
            + "VALUES (#{username},#{password},#{realname},#{email},#{phone},#{qq},#{type},#{auth},#{status},#{loginIp})")
    @Options(flushCache=true, useGeneratedKeys = true, keyProperty = "groupid")
    void addGroup(GroupInfo groupInfoEntity);

    @Update("UPDATE GroupInfo SET username = #{username},password=#{password},realname=#{realname},"
            + "email=#{email},"
            + "phone=#{phone},"
            + "qq=#{qq},"
            + "type=#{type},"
            + "auth=#{auth},status=#{status},loginIp=#{loginIp} WHERE id=#{id}")
    int updateGroup(GroupInfo groupInfoEntity);

    @Delete("DELETE FROM GroupInfo WHERE id=#{id}")
    int deleteGroup(Integer id);

    @Select("SELECT * FROM GroupInfo WHERE id = #{groupid}")
    @Options(useCache=true, flushCache=false)
    GroupInfo get(@Param("groupid") Integer groupid);

    @Select("SELECT * FROM GroupInfo limit 100")
    @Options(useCache = true, flushCache = false)
    List<GroupInfo> getAll();
}
