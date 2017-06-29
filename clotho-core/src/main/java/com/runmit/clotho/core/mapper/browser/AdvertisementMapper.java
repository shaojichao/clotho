package com.runmit.clotho.core.mapper.browser;

import com.runmit.clotho.core.domain.browser.Advertisement;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 开屏广告 Mapper
 * @author lgz
 * @version 1.0
 * @date 2017-06-06
 */
public interface AdvertisementMapper{

    /**
     * 新增开屏广告信息
     * @param ad 开屏广告信息对象
     */
    @Insert("INSERT INTO Ad(modeId,adURL,adLink,status,version,createBy,createTime,updateBy,updateTime) " +
            "VALUES(#{modeId},#{adURL},#{adLink},#{status},#{version},#{createBy},now(),#{updateBy},now())")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addAdvertisement(Advertisement ad);

    /**
     * 修改开屏广告信息
     * @param ad 开屏广告信息对象
     */
    @Update({"<script>",
            "UPDATE Ad set updateTime=now(),updateBy=#{updateBy}",
            "<if test='modeId != null '>",
            " ,modeId=#{modeId} ",
            "</if>",
            "<if test='adURL != null '>",
            " ,adURL=#{adURL} ",
            "</if>",
            "<if test='adLink != null '>",
            " ,adLink=#{adLink} ",
            "</if>",
            "<if test='status != null '>",
            " ,status=#{status} ",
            "</if>",
            "<if test='version != null '>",
            " ,version=#{version} ",
            "</if>",
            "<if test='status == 1 '>",
            " ,upShelvesTm=now() ",
            "</if>",
            "<if test='status == 2 '>",
            " ,downShelvesTm=now() ",
            "</if>",
            "where id=#{id}",
            "</script>"})
    @Options(flushCache = true)
    void updateAdvertisement(Advertisement ad);

    /**
     * 根据ID删除开屏广告信息
     * @param id 开屏广告ID
     */
    @Delete("DELETE FROM ad WHERE id=#{id}")
    @Options(flushCache = true)
    int deleteById(@Param("id") Integer id);

    /**
     * 分页查找所有开屏广告信息
     * @param start
     * @param limit
     * @return
     */
    @Select({"<script>",
            "SELECT a.*,CONCAT(p.width,'*',p.height) resolution ",
            "FROM Ad a ",
            "LEFT JOIN PhoneModel p ON p.id=a.modeId ",
            "ORDER BY updateTime DESC ",
            "LIMIT #{start},#{limit} ",
            "</script>"})
    @Options(useCache = true, flushCache = false)
    List<Advertisement> getAdList(@Param("start") int start, @Param("limit") int limit);

    /**
     * 查找开屏广告信息总条数
     * @return
     */
    @Select({"<script>",
            "SELECT count(1) ",
            "FROM Ad a ",
            "LEFT JOIN PhoneModel p ON p.id=a.modeId ",
            "</script>"})
    @Options(useCache = true, flushCache = false)
    int getAdCounts();

    /**
     * 根据ID查找开屏广告信息
     * @param id 主键
     * @return
     */
    @Select("SELECT * FROM Ad WHERE id=#{id} ")
    @Options(useCache = true, flushCache = false)
    Advertisement getById(@Param("id") int id);

    /**
     * 根据机型ID查找已上架状态的开屏广告信息集合
     * @param modeId 机型ID
     * @return
     */
    @Select({"<script>",
            "SELECT * FROM Ad ",
            "WHERE version=1 AND modeId=#{modeId} ",
            "</script>"})
    @Options(useCache = true, flushCache = false)
    Advertisement getInfoByModeId(@Param("modeId") Integer modeId);

    /**
     * 根据机型ID查找开屏广告信息集合
     * @param modeId 机型ID
     * @return
     */
    @Select({"<script>",
            "SELECT * FROM Ad ",
            "WHERE modeId=#{modeId} ",
            "<if test='version != null '>",
            "AND version=#{version} ",
            "</if>",
            "</script>"})
    @Options(useCache = true, flushCache = false)
    List<Advertisement> getAdListByModeId(@Param("modeId") int modeId,@Param("version") String version);

}
