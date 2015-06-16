package com.runmit.clotho.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.runmit.clotho.core.domain.client.Client;

/**
 *
 * @author zhipeng.tian
 */
@CacheNamespace(implementation = org.mybatis.caches.memcached.MemcachedCache.class)
public interface ClientMapper {

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
    Client getClient(@Param("clientId")int clientId);
}
