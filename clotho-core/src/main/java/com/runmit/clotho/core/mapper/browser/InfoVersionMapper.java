package com.runmit.clotho.core.mapper.browser;

import com.runmit.clotho.core.domain.browser.InfoVersion;
import org.apache.ibatis.annotations.*;

/**
 * 版本信息Mapper
 * @author lgz
 * @version 1.0
 * @date 2017-06-05
 */
public interface InfoVersionMapper{

    /**
     * 添加版本信息
     * @param version 版本信息对象
     * @author lgz
     * @version 1.0
     * @date 2017-06-05
     */
    @Insert("INSERT INTO InfoVersion("
            + "versionKey,versionNo,createBy,createTime,updateBy,updateTime) values "
            + "(#{versionKey},#{versionNo},#{createBy},now(),#{updateBy},now())")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addInfoVersion(InfoVersion version);

    /**
     * 修改版本信息
     * @param version 版本信息对象
     */
    @Update("UPDATE InfoVersion set versionNo=#{versionNo},updateTime=now(),updateBy=#{updateBy} " +
            "where id=#{id}")
    @Options(flushCache = true)
    void updateInfoVersion(InfoVersion version);

    /**
     * 根据ID删除版本信息
     * @param id 版本ID
     */
    @Delete("DELETE FROM InfoVersion WHERE id=#{id}")
    @Options(flushCache = true)
    int deleteById(@Param("id") Integer id);

    /**
     * 根据ID查找版本信息
     * @param id 版本ID
     * @return
     */
    @Select("SELECT * FROM InfoVersion WHERE id=#{id}")
    @Options(useCache = true, flushCache = false)
    InfoVersion selectById(@Param("id") Integer id);

    /**
     * 根据版本key查找版本信息
     * @param versionKey 版本key
     * @return
     */
    @Select("SELECT * FROM InfoVersion WHERE versionKey=#{versionKey}")
    @Options(useCache = true, flushCache = false)
    InfoVersion selectByVersionKey(@Param("versionKey") String versionKey);

}
