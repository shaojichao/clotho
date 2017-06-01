package com.runmit.clotho.core.mapper.browser;

import com.runmit.clotho.core.domain.browser.SearchEngine;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 搜索引擎mapper
 */
public interface SearchEngineMapper{

    /**
     * 添加搜索引擎
     * @param engine 搜索引擎信息对象
     */
    @Insert("INSERT INTO SearchEngine("
            + "name,engineURL,channelNO,icon,status,"
            + "createBy,createTime,updateBy,updateTime) values "
            + "(#{name},#{engineURL},#{channelNO},#{icon},#{status},"
            + "#{createBy},now(),#{updateBy},now())")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addSearchEngine(SearchEngine engine);

    /**
     * 修改搜索引擎
     * @param engine 搜索引擎信息对象
     */
    @Update("UPDATE SearchEngine set name=#{name},icon=#{icon},channelNO=#{channelNO}," +
            "status=#{status},engineURL=#{engineURL},updateTime=now(),updateBy=#{updateBy} " +
            "where id=#{id}")
    @Options(flushCache = true)
    void updateSearchEngine(SearchEngine engine);

    /**
     * 根据ID删除搜索引擎信息
     * @param id 引擎ID
     */
    @Delete("DELETE FROM SearchEngine WHERE id=#{id}")
    @Options(flushCache = true)
    void deleteById(@Param("id") Integer id);

    /**
     * 根据ID查找搜索引擎信息
     * @param id 引擎ID
     * @return
     */
    @Select("SELECT * FROM SearchEngine WHERE id=#{id}")
    @Options(useCache = true, flushCache = false)
    SearchEngine selectById(@Param("id") Integer id);

    /**
     * 查找所有搜索引擎信息集合
     * @return
     */
    @Select("SELECT * FROM SearchEngine LIMIT #{start},#{limit}")
    @Options(useCache = true, flushCache = false)
    List<SearchEngine> getList(@Param("start") int start, @Param("limit") int limit);

    /**
     * 查找所有搜索引擎信息总条数
     * @return
     */
    @Select("SELECT count(1) FROM SearchEngine")
    @Options(useCache = true, flushCache = false)
    long getCounts();
}
