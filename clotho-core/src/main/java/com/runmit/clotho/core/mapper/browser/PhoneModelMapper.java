package com.runmit.clotho.core.mapper.browser;

import com.runmit.clotho.core.domain.browser.PhoneModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author sjc
 * @date 2017-06-01-10:33
 */
public interface PhoneModelMapper {

    /**
     * 根据机型名称分页查询机型信息
     * @param model 机型名称
     * @param page
     * @param limit
     * @return
     */
    @Select({"<script>",
            "SELECT * FROM PhoneModel ",
            "<if test='model != null'>",
            " WHERE model like CONCAT('%',#{model},'%') ",
            "</if>",
            "ORDER BY createTime DESC ",
            "LIMIT #{page},#{limit} ",
            "</script>"})
    @Options(useCache = true, flushCache = false)
    List<PhoneModel> getPhoneModelPage(@Param("model") String model, @Param("page") int page, @Param("limit") int limit);

    /**
     * 查出所有机型对应的分辨率信息
     * @return
     */
    @Select("SELECT id,CONCAT(width,'*',height) resolution FROM PhoneModel")
    @Options(useCache = true, flushCache = false)
    List<PhoneModel> getPhoneModelList();

    /**
     * 查出机型的条数
     * @param model 机型名字
     * @return
     */
    @Select({"<script>",
            "SELECT count(1) FROM PhoneModel ",
            "<if test='model != null'>",
            " WHERE model like CONCAT('%',#{model},'%') ",
            "</if>",
            "</script>"})
    @Options(useCache = true, flushCache = false)
    int getPhoneModelCount(@Param("model") String model);

    /**
     * 新增机型信息
     * @param phoneModel
     */
    @Insert("INSERT INTO PhoneModel(model,width,height,createBy,createTime,updateBy,updateTime) " +
            "VALUES(#{model},#{width},#{height},#{createBy},now(),#{updateBy},now())")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addPhoneModel(PhoneModel phoneModel);

    /**
     * 修改机型信息
     * @param phoneModel
     */
    @Update("UPDATE PhoneModel " +
            "SET model=#{model},width=#{width},height=#{height},updateTime=now(),updateBy=#{updateBy} " +
            "where id=#{id}")
    @Options(flushCache = true)
    void updatePhoneModel(PhoneModel phoneModel);

    /**
     * 根据id删除对应机型信息
     * @param id
     * @return
     */
    @Delete("DELETE FROM PhoneModel WHERE id=#{id}")
    @Options(flushCache = true)
    int deletePhoneModelById(@Param("id") Integer id);

    /**
     * 根据分辨率查找机型分辨率信息对象
     * @param width 分辨率宽
     * @param height 分辨率高
     * @return 返回机型分辨率信息对象
     * @author lgz
     * @date 2017-06-29
     */
    @Select("SELECT * FROM PhoneModel WHERE width=#{width} AND height=#{height}")
    @Options(useCache = true, flushCache = false)
    PhoneModel getModelByResolution(@Param("width") Integer width,@Param("height") Integer height);

    /**
     * 根据id查找机型信息对象
     * @param id 机型ID
     * @return 返回机型信息对象
     * @author lgz
     * @date 2017-06-07
     */
    @Select("SELECT * FROM PhoneModel WHERE id=#{id}")
    @Options(useCache = true, flushCache = false)
    PhoneModel getModelById(@Param("id") Integer id);

}
