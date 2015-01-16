package com.runmit.clotho.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.runmit.clotho.core.domain.upgrade.UpgradePlan;
import com.runmit.clotho.core.domain.upgrade.Version;

/**
 *
 * @author Scott.Xie
 */
public interface VersionMapper {


    @Select("SELECT * FROM UpgradePlan where originid=(select serialno from Version where version=#{version}) and clientId=#{clientId} order by upgradeid desc limit 1 ")
    @Options(useCache = true, flushCache = false)
    UpgradePlan getUpgradePlanbyversion(@Param("version") String version,@Param("clientId") int clientId);
    
    @Select("SELECT * FROM UpgradePlan where id=#{id}")
    UpgradePlan getUpgradePlabById(@Param("id")int id);
    
    
    @Select("SELECT * FROM UpgradePlan where originid=#{originid} and upgradeid=#{upgradeid} order by upgradeid desc limit 1 ")
    @Options(useCache = true, flushCache = false)
    UpgradePlan getUpgradePlan(@Param("originid") String originid,@Param("upgradeid") String upgradeid);
    
    @Select("SELECT UpgradePlan.*,version FROM UpgradePlan left join Version on originid = serialno "
    		+ "where upgradeid=(select serialno from Version where version=#{version}) "
    		+ "order by upgradeid desc")
    @Options(useCache = true, flushCache = false)
    List<UpgradePlan> getUpgradePlans(@Param("version") String version);

    @Select("SELECT * FROM Version WHERE id=#{id}")
    @Options(useCache = true, flushCache = false)
    Version getbyid(@Param("id") int id);
    
    @Select("SELECT * FROM Version ORDER BY id DESC LIMIT #{start},#{limit}")
    @Options(useCache = true, flushCache = false)
    List<Version> getList(@Param("start")int start,@Param("limit")int limit);
    
    @Select("SELECT count(id) FROM Version")
    @Options(useCache = true, flushCache = false)
    long getCount();

    @Select("SELECT * FROM Version WHERE version=#{version} and clientId=#{clientId}")
    @Options(useCache = true, flushCache = false)
    Version getbyversion(@Param("version") String version,@Param("clientId") int clientId);

    @Select("SELECT * FROM Version WHERE serialno=#{serialno}")
    @Options(useCache = true, flushCache = false)
    Version getbyserialno(@Param("serialno") String serialno);

    @Select("SELECT * FROM Version WHERE clientid=#{clientid} order by serialno desc limit 1")
    @Options(useCache = true, flushCache = false)
    Version getLastestbyclientid(@Param("clientid") int clientid);

    @Insert("INSERT INTO Version (`version`,`serialno`,`memo`,`pkgurl`,`clientid`,`showtype`,`upgradetype`,`createby`) "
            + "VALUES (#{version},#{serialno},#{memo},#{pkgurl},#{clientid},#{showtype},#{upgradetype},#{createby})")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addVersion(Version version);
    
    @Insert("UPDATE Version SET `version`=#{version},`serialno`=#{serialno},`memo`=#{memo},`pkgurl`=#{pkgurl},"
    		+ "`clientid`=#{clientid},`showtype`=#{showtype},`upgradetype`=#{upgradetype},`updateby`=#{updateby},`updatetime`=now()"
    		+ " where `id`=#{id}")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void updateVersion(Version version);
    
    @Delete("DELETE FROM Version WHERE id=#{id}")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void delVersion(@Param("id") int id);
    
    @Delete("DELETE FROM UpgradePlan WHERE id=#{id}")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void delPlan(@Param("id") int id);
    
    @Insert("INSERT INTO UpgradePlan (`originid`,`upgradeid`,`memo`,`clientid`,`showtype`,`upgradetype`,`createby`) "
            + "VALUES (#{originid},#{upgradeid},#{memo},#{clientid},#{showtype},#{upgradetype},#{createby})")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addPlan(UpgradePlan plan);
    
    @Insert("UPDATE UpgradePlan SET `originid`=#{originid},`memo`=#{memo},"
    		+ "`clientid`=#{clientid},`showtype`=#{showtype},`upgradetype`=#{upgradetype},`updateby`=#{updateby},`updatetime`=now()"
    		+ " where `id`=#{id}")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void updatePlan(UpgradePlan plan);
}
