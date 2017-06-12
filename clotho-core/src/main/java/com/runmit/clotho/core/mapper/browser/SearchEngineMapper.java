package com.runmit.clotho.core.mapper.browser;

import com.runmit.clotho.core.domain.browser.SearchEngine;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 搜索引擎 Mapper
 * @author lgz
 * @version 1.0
 * @date 2017-06-06
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
    int deleteById(@Param("id") Integer id);

    /**
     * 根据ID查找搜索引擎信息
     * @param id 引擎ID
     * @return
     */
    @Select("SELECT * FROM SearchEngine WHERE id=#{id}")
    @Options(useCache = true, flushCache = false)
    SearchEngine selectById(@Param("id") Integer id);

    /**
     * 查找默认搜索引擎信息
     * @return
     */
    @Select("SELECT * FROM SearchEngine WHERE status=#{status}")
    @Options(useCache = true, flushCache = false)
    List<SearchEngine> getDefaultEngine(@Param("status") int status);

    /**
     * 查找所有搜索引擎信息
     * @return
     */
    @Select("SELECT * FROM SearchEngine")
    @Options(useCache = true, flushCache = false)
    List<SearchEngine> selectAll();

    /**
     * 分页查找所有搜索引擎信息集合
     * @return
     */
    @Select({"<script>",
            "SELECT *,(CASE ",
            "WHEN status='0' THEN '否' ",
            "WHEN status = '1' THEN '是' ",
            "ELSE '未定义' END) statusName",
            "from SearchEngine ",
            "ORDER BY updateTime DESC",
            "LIMIT #{start},#{limit} ",
            "</script>"})
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
