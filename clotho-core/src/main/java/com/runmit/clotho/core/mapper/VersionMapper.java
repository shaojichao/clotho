package com.runmit.clotho.core.mapper;

import com.runmit.clotho.core.domain.upgrade.UpgradePlan;
import com.runmit.clotho.core.domain.upgrade.Version;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @author Scott.Xie
 */
public interface VersionMapper {

//    @Insert("INSERT INTO DeviceInfo (`devicesn`,`devicetype`,`location`,`produceddate`,`status`,`createby`,`updateby`,`updatetime`) "
//            + "VALUES (#{devicesn},#{devicetype},#{location},#{produceddate},#{status},#{createby},#{updateby},#{updatetime})")
//    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "deviceid")
//    void addDeviceInfo(DeviceInfo DeviceInfoEntity);
////
//    @Update("UPDATE DeviceInfo SET devicesn=#{devicesn},devicetype=#{devicetype},location=#{location},produceddate=#{produceddate},status=#{status},updateby=#{updateby},updatetime=#{updatetime} where deviceid=#{deviceid}")
//    @Options(flushCache = true)
//    void updateDeviceInfo(DeviceInfo DeviceInfoEntity);
//
//    @Update("UPDATE DeviceInfo SET userregcount=userregcount+1 where devicesn=#{devicesn}")
//    @Options(flushCache = true)
//    void addDeviceRegUser(@Param("devicesn") String devicesn);
//
//    @Delete("DELETE FROM DeviceInfo WHERE deviceid=#{deviceid}")
//    @Options(flushCache = true)
//    int deleteDeviceInfo(@Param("deviceid") Integer deviceid);

    @Select("SELECT * FROM UpgradePlan where originid=(select serialno from Version where version=#{version}) order by upgradeid desc limit 1 ")
    @Options(useCache = true, flushCache = false)
    UpgradePlan getUpgradePlanbyversion(@Param("version") String version);

    @Select("SELECT * FROM Version WHERE id=#{id}")
    @Options(useCache = true, flushCache = false)
    Version getbyid(@Param("id") int id);

    @Select("SELECT * FROM Version WHERE version=#{version}")
    @Options(useCache = true, flushCache = false)
    Version getbyversion(@Param("version") String version);

    @Select("SELECT * FROM Version WHERE serialno=#{serialno}")
    @Options(useCache = true, flushCache = false)
    Version getbyserialno(@Param("serialno") String serialno);

    @Select("SELECT * FROM Version WHERE clientid=#{clientid} order by serialno desc limit 1")
    @Options(useCache = true, flushCache = false)
    Version getLastestbyclientid(@Param("clientid") int clientid);

//    @Select("SELECT * FROM DeviceInfo WHERE devicesn=#{devicesn}")
////    @Options(useCache = true, flushCache = false)
//    DeviceInfo getbysn(@Param("devicesn") String devicesn);
//
//    @Select("SELECT * FROM DeviceInfo")
//    List<DeviceInfo> getAll();

//    @Select("SELECT * FROM UserDeviceRela WHERE  id=#{id}")
//    @Options(useCache = true, flushCache = false)
//    List<UserDeviceRela> getRelabyId(@Param("id") Integer id);
}
