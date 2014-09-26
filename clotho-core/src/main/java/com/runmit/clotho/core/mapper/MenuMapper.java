package com.runmit.clotho.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.runmit.clotho.core.domain.Menu;

/**
 *
 * @author Scott.Xie
 */
public interface MenuMapper {

    @Insert("INSERT INTO Menu (`text`,`parentId`,`url`,`leaf`,`status`,`createdBy`) "
            + "VALUES (#{text},#{parentId},#{url},#{leaf},#{status},#{createdBy})")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addMenu(Menu menu);
    
    @Select("SELECT Menu.* FROM Menu where parentID=#{parentID}")
    List<Menu> getList(@Param("parentID")int parentID);
    
}
