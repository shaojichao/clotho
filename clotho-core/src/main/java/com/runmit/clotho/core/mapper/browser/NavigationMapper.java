package com.runmit.clotho.core.mapper.browser;

import com.runmit.clotho.core.domain.browser.Navigation;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 导航栏Mapper
 * @author lgz
 * @version 1.0
 * @date 2017-06-06
 */
public interface NavigationMapper{

    /**
     * 添加导航栏
     * @param nav 导航栏信息对象
     */
    @Insert("INSERT INTO Navigation("
            + "name,type,appIdOrUrl,icon,position,"
            + "createBy,createTime,updateBy,updateTime) values "
            + "(#{name},#{type},#{appIdOrUrl},#{icon},#{position},"
            + "#{createBy},now(),#{updateBy},now())")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addNavigation(Navigation nav);

    /**
     * 根据ID修改导航栏信息排序字段
     * @param id
     */
    @Update("UPDATE Navigation SET position =#{id} WHERE id=#{id}")
    @Options(flushCache = true)
    void updateSort(@Param("id") Integer id);

    /**
     * 修改导航栏
     * @param nav 导航栏信息对象
     */
    @Update("UPDATE Navigation set type=#{type},name=#{name},icon=#{icon},appIdOrUrl=#{appIdOrUrl}," +
            "updateTime=now(),updateBy=#{updateBy} where id=#{id}")
    @Options(flushCache = true)
    void updateNavigation(Navigation nav);

    /**
     * 根据ID删除导航栏信息
     * @param id 导航栏ID
     */
    @Delete("DELETE FROM Navigation WHERE id=#{id}")
    @Options(flushCache = true)
    int deleteById(@Param("id") Integer id);

    /**
     * 根据ID查找导航栏信息
     * @param id 导航栏ID
     * @return
     */
    @Select("SELECT * FROM Navigation WHERE id=#{id}")
    @Options(useCache = true, flushCache = false)
    Navigation selectById(@Param("id") Integer id);

    /**
     * 分页查找所有导航栏信息集合
     * @return
     */
    @Select({"<script>",
            "SELECT * FROM Navigation ",
            "<if test='name != null '>",
            " where name like CONCAT('%',#{name},'%') ",
            "</if>",
            "ORDER BY position DESC",
            "LIMIT #{start},#{limit} ",
            "</script>"})
    @Options(useCache = true, flushCache = false)
    List<Navigation> getList(@Param("name") String name,@Param("start") int start, @Param("limit") int limit);

    /**
     * 分页查找所有导航栏信息总条数
     * @return
     */
    @Select({"<script>",
            "SELECT count(1) FROM Navigation ",
            "<if test='name != null '>",
            " where name like CONCAT('%',#{name},'%'))",
            "</if>",
            "</script>"})
    @Options(useCache = true, flushCache = false)
    int getCounts(@Param("name") String name);
}
