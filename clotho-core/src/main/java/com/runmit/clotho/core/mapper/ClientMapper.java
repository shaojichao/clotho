package com.runmit.clotho.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.runmit.clotho.core.domain.client.Client;

/**
 *
 * @author zhipeng.tian
 */
public interface ClientMapper {

    @Insert("INSERT INTO Client (`name`,`description`,`createby`) "
            + "VALUES (#{name},#{description},#{createby})")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addClient(Client client);
    
    @Insert("UPDATE Client set `name`=#{name},`description`=#{description},`updatetime`=now(),`updateby`=#{updateby} where clientId=#{clientId}")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void updateClient(Client client);
    
    @Select("SELECT * FROM Client")
    List<Client> getList();
}
