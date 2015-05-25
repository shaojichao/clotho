package com.runmit.clotho.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.runmit.clotho.core.domain.client.App;

/**
 *
 * @author zhipeng.tian
 */
public interface AppMapper {

    @Insert("INSERT INTO App (`name`,`description`,`createby`) "
            + "VALUES (#{name},#{description},#{createby})")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "appId")
    void addApp(App App);
    
    @Insert("UPDATE App set `name`=#{name},`description`=#{description},`updatetime`=now(),`updateby`=#{updateby} where appId=#{appId}")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "appId")
    void updateApp(App app);
    
    @Select("SELECT * FROM App")
    List<App> getList();
    
    @Select("SELECT * FROM App where appId=#{appId}")
    App getApp(@Param("appId")int appId);
}
