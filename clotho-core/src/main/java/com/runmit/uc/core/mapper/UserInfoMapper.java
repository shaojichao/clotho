package com.runmit.uc.core.mapper;

import com.runmit.uc.core.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *
 * @author TianLiang
 */
@CacheNamespace(implementation = org.mybatis.caches.memcached.MemcachedCache.class)
public interface UserInfoMapper {

    @Insert("INSERT INTO UserInfo (`userid`,`useridtype`,`password`,`name`,`nickname`,`age`,`gender`,`mobile`,`mail`,`occupation`,`address`,`viplevel`,`location`,`headposter`,`validata`,`status`,`createby`,`createtime`,`updateby`,`updatetime`) "
            + "VALUES (#{userid},#{useridtype},#{password},#{name},#{nickname},#{age},#{gender},#{mobile},#{mail},#{occupation},#{address},#{viplevel},#{location},#{headposter},#{validata},#{status},#{createby},#{createtime},#{updateby},#{updatetime})")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addUserInfo(UserInfo UserInfoEntity);

    @Update("UPDATE UserInfo SET userid = #{userid},useridtype=#{useridtype},password=#{password},name=#{name},"
            + "nickname=#{nickname},age=#{age},gender=#{gender},mobile=#{mobile},"
            + "mail=#{mail},occupation=#{occupation},address=#{address},viplevel=#{viplevel},"
            + "location=#{location},headposter=#{headposter},validata=#{validata},status=#{status},updateby=#{updateby},"
            + "updatetime=#{updatetime} WHERE id=#{id}")
    @Options(flushCache = true)
    int updateUser(UserInfo UserInfoEntity);

    @Delete("DELETE FROM UserInfo WHERE id=#{id}")
    @Options(flushCache = true)
    void deleteUser(Integer id);

    @Select("SELECT * FROM UserInfo WHERE id = #{id}")
    @Options(useCache = true, flushCache = false)
    UserInfo get(@Param("id") Integer id);

    @Select("SELECT * FROM UserInfo WHERE userid = #{userid}")
    @Options(useCache = true, flushCache = false)
    UserInfo getbyUserId(@Param("userid") String userid);

    @Select("SELECT * FROM UserInfo WHERE userid = #{mail} or mail = #{mail}")
    @Options(useCache = true, flushCache = false)
    List<UserInfo> getbyMail(@Param("mail") String mail);

    @Select("SELECT * FROM UserInfo WHERE userid = #{mobile} or mobile = #{mobile}")
    @Options(useCache = true, flushCache = false)
    List<UserInfo> getbyMobile(@Param("mobile") String mobile);

    @Select("SELECT * FROM UserInfo limit 100")
    @Options(useCache = true, flushCache = false)
    List<UserInfo> getAll();
}
