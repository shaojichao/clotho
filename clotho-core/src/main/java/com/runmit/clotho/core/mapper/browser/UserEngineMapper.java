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

    /**
     * 分页查找用户搜索引擎信息集合
     * @return
     */
    @Select({"<script>",
            "SELECT * FROM UserEngine WHERE 1=1 ",
            "<if test='account != null '>",
            " and account =#{account} ",
            "</if>",
            "<if test='engineId != null '>",
            " and engineId =#{engineId} ",
            "</if>",
            "ORDER BY updateTime DESC",
            "LIMIT #{start},#{limit} ",
            "</script>"})
    @Options(useCache = true, flushCache = false)
    List<UserEngine> getList(@Param("account") String account,@Param("engineId") Integer engineId,@Param("start") int start, @Param("limit") int limit);

    /**
     * 分页查找用户搜索引擎信息总条数
     * @return
     */
    @Select({"<script>",
            "SELECT count(1) FROM UserEngine WHERE 1=1 ",
            "<if test='account != null '>",
            " and account =#{account} ",
            "</if>",
            "<if test='engineId != null '>",
            " and engineId =#{engineId} ",
            "</if>",
            "</script>"})
    @Options(useCache = true, flushCache = false)
    int getCounts(@Param("name") String name);

}
