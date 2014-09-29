package com.runmit.clotho.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.runmit.clotho.core.domain.admin.Menu;

/**
 *
 * @author Scott.Xie
 */
public interface MenuMapper {

    @Insert("INSERT INTO Menu (`text`,`parentId`,`url`,`leaf`,`status`,`createdBy`) "
            + "VALUES (#{text},#{parentID},#{url},#{leaf},#{status},#{createdBy})")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addMenu(Menu menu);
    
    @Insert("UPDATE Menu set `text`=#{text},`parentId`=#{parentID},`url`=#{url},`leaf`=#{leaf},`status`=#{status} where id=#{id}")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void updateMenu(Menu menu);
    
    @Insert("UPDATE Menu set `status`='INACTIVE' where id=#{id}")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void delMenu(@Param("id")int id);
    
    @Select("SELECT Menu.* FROM Menu where parentid=#{parentID} and status='ACTIVE'")
    List<Menu> getList(@Param("parentID")int parentID);
    
    @Select("SELECT a.*,b.text as parentName FROM Menu a left join Menu b on a.parentid=b.id")
    List<Menu> getAllMenu();
    
}
