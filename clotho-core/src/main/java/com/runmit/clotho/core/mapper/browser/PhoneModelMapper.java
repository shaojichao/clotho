package com.runmit.clotho.core.mapper.browser;

import com.runmit.clotho.core.domain.client.Client;
import com.runmit.clotho.core.domain.browser.PhoneModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author sjc
 * @date 2017-06-01-10:33
 */
public interface PhoneModelMapper {

    @Insert("INSERT INTO Client (`name`,`superProjectId`,`description`,`createby`) "
            + "VALUES (#{name},#{superProjectId},#{description},#{createby})")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addClient(Client client);
    
    @Insert("UPDATE Client set `name`=#{name},`superProjectId`=#{superProjectId},`description`=#{description},`updatetime`=now(),`updateby`=#{updateby} where clientId=#{clientId}")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void updateClient(Client client);
    
    @Select("SELECT * FROM Client")
    @Options(useCache = true, flushCache = false)
    List<Client> getList();
    
    @Select("SELECT * FROM Client where clientId=#{clientId}")
    @Options(useCache = true, flushCache = false)
    Client getClient(@Param("clientId") int clientId);

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

    @Select({"<script>",
            "SELECT count(1) FROM PhoneModel ",
            "<if test='model != null'>",
            " WHERE model like CONCAT('%',#{model},'%') ",
            "</if>",
            "</script>"})
    @Options(useCache = true, flushCache = false)
    int getPhoneModelCount(@Param("model") String model);
}
