package com.runmit.clotho.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.runmit.clotho.core.domain.upgrade.UpgradePlan;
import com.runmit.clotho.core.domain.upgrade.UpgradePlanMemo;
import com.runmit.clotho.core.domain.upgrade.Version;

/**
 *
 * @author Scott.Xie
 */
public interface VersionMapper {

	@Select("SELECT * FROM UpgradePlan where (originid='' or originid LIKE CONCAT('%,',(SELECT serialno FROM Version WHERE version=#{version} AND clientId=#{clientId}),',%')) and clientId=#{clientId} order by upgradeid desc limit 1 ")
	@Options(useCache = true, flushCache = false)
	UpgradePlan getUpgradePlanbyversion(@Param("version") String version,
			@Param("clientId") int clientId);

	@Select("SELECT * FROM UpgradePlan where id=#{id}")
	UpgradePlan getUpgradePlabById(@Param("id") int id);

	@Select("SELECT * FROM UpgradePlan where originid=#{originid} and upgradeid=#{upgradeid} order by upgradeid desc limit 1 ")
	@Options(useCache = true, flushCache = false)
	UpgradePlan getUpgradePlan(@Param("originid") String originid,
			@Param("upgradeid") String upgradeid);

	@Select("SELECT UpgradePlan.*,version FROM UpgradePlan left join Version on originid = serialno "
			+ "where upgradeid=(select serialno from Version where version=#{version} and clientid=#{clientid}) "
			+ "order by upgradeid desc")
	@Options(useCache = true, flushCache = false)
	List<UpgradePlan> getUpgradePlans(@Param("version") String version,
			@Param("clientid") int clientid);

	@Select("SELECT * FROM Version WHERE id=#{id}")
	@Options(useCache = true, flushCache = false)
	Version getbyid(@Param("id") int id);

	@Select("SELECT ver.*,cli.name as clientname FROM Version ver left join Client cli on ver.clientid = cli.clientId where ver.clientid=#{clientid} ORDER BY ver.id DESC LIMIT #{start},#{limit}")
	@Options(useCache = true, flushCache = false)
	List<Version> getList(@Param("start") int start, @Param("limit") int limit,
			@Param("clientid") int clientid);

	@Select("SELECT count(id) FROM Version where clientid=#{clientid}")
	@Options(useCache = true, flushCache = false)
	long getCount(@Param("clientid") int clientid);

	@Select("SELECT * FROM Version WHERE version=#{version} and clientid=#{clientid}")
	@Options(useCache = true, flushCache = false)
	Version getbyversion(@Param("version") String version,
			@Param("clientid") int clientid);

    @Select("<script>SELECT * FROM Version " +
            "WHERE 1=1 " +
            "<if test=\"brand != null\"> AND brand = #{brand} </if>" +
            "<if test=\"model != null\"> AND model = #{model} </if>" +
            "<if test=\"country != null\"> AND country = #{country} </if>" +
            "<if test=\"hardwareVersion != null\"> AND hardwareVersion = #{hardwareVersion} </if>" +
            "<if test=\"version != null\"> AND version = #{version} </if> " +
            "</script>" )
    @Options(useCache = true, flushCache = false)
    Version getOtaVersion(@Param("brand") String brand,@Param("model") String model,@Param("country") String country,
                         @Param("hardwareVersion") String hardwareVersion,@Param("version") String version);

	@Select("SELECT * FROM Version WHERE serialno=#{serialno}")
	@Options(useCache = true, flushCache = false)
	Version getbyserialno(@Param("serialno") String serialno);

	@Select("SELECT * FROM Version WHERE clientid=#{clientid} order by serialno desc limit 1")
	@Options(useCache = true, flushCache = false)
	Version getLastestbyclientid(@Param("clientid") int clientid);

	@Insert("INSERT INTO Version (`version`,`serialno`,`pkgurl`,`filesize`,`clientid`,`showtype`,`upgradetype`,`createby`) "
			+ "VALUES (#{version},#{serialno},#{pkgurl},#{filesize},#{clientid},#{showtype},#{upgradetype},#{createby})")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void addVersion(Version version);

	@Insert("UPDATE Version SET `version`=#{version},`serialno`=#{serialno},`pkgurl`=#{pkgurl},`filesize`=#{filesize},"
			+ "`clientid`=#{clientid},`showtype`=#{showtype},`upgradetype`=#{upgradetype},`updateby`=#{updateby},`updatetime`=now()"
			+ " where `id`=#{id}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void updateVersion(Version version);

    @Insert("INSERT INTO Version (`version`,`serialno`,`pkgurl`,`filesize`,`clientid`,`showtype`,`upgradetype`,`createby`,`brand`,`model`,`country`,`hardwareVersion`,`latitude`) "
            + "VALUES (#{version},#{serialno},#{pkgurl},#{filesize},#{clientid},#{showtype},#{upgradetype},#{createby},#{brand},#{model},#{country},#{hardwareVersion},#{latitude})")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addOtaVersion(Version version);

    @Insert("UPDATE Version SET `version`=#{version},`serialno`=#{serialno},`pkgurl`=#{pkgurl},`filesize`=#{filesize},"
            + "`showtype`=#{showtype},`upgradetype`=#{upgradetype},`updateby`=#{updateby},`updatetime`=now(),"
            + "`brand`=#{brand},`model`=#{model},`country`=#{country},`hardwareVersion`=#{hardwareVersion},`latitude`=#{latitude}"
            + " where `id`=#{id}")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void updateOtaVersion(Version version);

	@Delete("DELETE FROM Version WHERE id=#{id}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void delVersion(@Param("id") int id);

	@Delete("DELETE FROM UpgradePlan WHERE id=#{id}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void delPlan(@Param("id") int id);
	
	@Delete("DELETE FROM UpgradePlan WHERE upgradeid=#{upgradeid}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void delPlanByUpgradeid(@Param("upgradeid") String upgradeid);

	@Insert("INSERT INTO UpgradePlan (`originid`,`versions`,`upgradeid`,`clientid`,`showtype`,`upgradetype`,`createby`) "
			+ "VALUES (#{originid},#{versions},#{upgradeid},#{clientid},#{showtype},#{upgradetype},#{createby})")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void addPlan(UpgradePlan plan);

	@Insert("UPDATE UpgradePlan SET `originid`=#{originid},`versions`=#{versions},"
			+ "`clientid`=#{clientid},`showtype`=#{showtype},`upgradetype`=#{upgradetype},`updateby`=#{updateby},`updatetime`=now()"
			+ " where `id`=#{id}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void updatePlan(UpgradePlan plan);

	@Insert("insert into UpgradePlanMemo (`planid`,`language`,`memo`,`createby`) values (#{planid},#{language},#{memo},#{createby})")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void savePlanMemo(UpgradePlanMemo memo);

	@Insert("UPDATE UpgradePlanMemo set `planid`=#{planid},`language`=#{language},`memo`=#{memo},`updateby`=#{updateby},`updatetime`=now() where `id`=#{id}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void updatePlanMemo(UpgradePlanMemo memo);

	@Delete("DELETE FROM UpgradePlanMemo WHERE id=#{id}")
	@Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	void delPlanMemo(@Param("id") int id);
	
	@Select("select * from UpgradePlanMemo where planid=#{planid}")
	@Options(useCache = true, flushCache = false)
	List<UpgradePlanMemo> getMemos(@Param("planid") int planid);
	
	@Select("select * from UpgradePlanMemo where id=#{id}")
	@Options(useCache = true, flushCache = false)
	UpgradePlanMemo getMemo(@Param("id") int id);
	
	@Select("select * from UpgradePlanMemo where planid=#{planid} and language=#{lang}")
	UpgradePlanMemo getMemoByLang(@Param("planid") int planid,@Param("lang") String lang);
	
	@Select("select count(id) from UpgradePlanMemo where planid=#{planid} and language=#{language}")
	long getMemoCount(@Param("planid") int planid,@Param("language") String language);
}
