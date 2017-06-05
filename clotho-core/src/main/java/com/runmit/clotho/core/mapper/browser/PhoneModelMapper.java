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
     * 分页查出所有机型信息/根据机型名称查出对应信息
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
    List<PhoneModel> getPhoneModelList(@Param("model") String model, @Param("page") int page, @Param("limit") int limit);

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

}
