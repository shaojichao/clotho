package com.runmit.clotho.core.mapper.browser;

import com.runmit.clotho.core.domain.browser.UserEngine;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户搜索引擎关系 Mapper
 * @author lgz
 * @version 1.0
 * @date 2017-06-06
 */
public interface UserEngineMapper{

    /**
     * 添加用户默认搜索引擎
     * @param userEngine 用户默认搜索引擎信息对象
     */
    @Insert("INSERT INTO UserEngine("
            + "userId,account,engineId,createBy,createTime,updateBy,updateTime) values "
            + "(#{userId},#{account},#{engineId},#{createBy},now(),#{updateBy},now())")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    int addUserEngine(UserEngine userEngine);

    /**
     * 修改用户默认搜索引擎
     * @param userEngine 用户默认搜索引擎信息对象
     */
    @Update("UPDATE UserEngine set engineId=#{engineId},updateBy=#{updateBy},updateTime=#{updateTime} where userId=#{userId}")
    @Options(flushCache = true)
    void updateUserEngine(UserEngine userEngine);

    /**
     * 根据userId查找用户默认搜索引擎信息
     * @param userId 用户ID
     * @return
     */
    @Select("SELECT * FROM UserEngine WHERE userId=#{userId}")
    @Options(useCache = true, flushCache = false)
    List<UserEngine> getUserDefaultEngine(@Param("userId") int userId);

}
