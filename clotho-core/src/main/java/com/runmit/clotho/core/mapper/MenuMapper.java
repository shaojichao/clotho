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

    @Insert("INSERT INTO Menu (`text`,`parentId`,`url`,`leaf`,`status`,`createdBy`,`orderNum`) "
            + "VALUES (#{text},#{parentID},#{url},#{leaf},#{status},#{createdBy},#{orderNum})")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addMenu(Menu menu);
    
    @Insert("UPDATE Menu set `text`=#{text},`parentId`=#{parentID},`url`=#{url},`leaf`=#{leaf},`status`=#{status},`orderNum`=#{orderNum} where id=#{id}")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void updateMenu(Menu menu);
    
    @Insert("UPDATE Menu set `status`='INACTIVE' where id=#{id}")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void delMenu(@Param("id")int id);
    
    @Select("SELECT Menu.* FROM Menu where parentid=#{parentID} and status='ACTIVE' order by orderNum")
    List<Menu> getList(@Param("parentID")int parentID);
    
    @Select("SELECT a.*,b.text as parentName FROM Menu a left join Menu b on a.parentid=b.id order by a.parentID,a.orderNum,a.id")
    List<Menu> getAllMenu();
    
    @Select("SELECT a.*,b.roleid FROM (SELECT * FROM Menu WHERE parentID = #{parentID} AND STATUS = 'ACTIVE') a left join (SELECT * FROM RoleMenuMember WHERE roleid = #{roleid}) b on a.id=b.menuid ORDER BY a.orderNum,a.id")
    List<Menu> getListByRole(@Param("parentID")int parentID,@Param("roleid")int roleid);
    
    @Select("SELECT a.* FROM Menu a,RoleMenuMember b,AdminRoleMember c where a.parentid=#{parentID} and a.status='ACTIVE'"
    		+ " and a.id=b.menuid and c.roleid=b.roleid and c.adminid=#{adminid} ORDER BY a.orderNum,a.id")
    List<Menu> getListByAdminid(@Param("adminid")int adminid,@Param("parentID")int parentID);
}
