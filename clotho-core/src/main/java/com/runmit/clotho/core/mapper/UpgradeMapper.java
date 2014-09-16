package com.runmit.clotho.core.mapper;

import com.runmit.clotho.core.domain.Upgrade;
import com.runmit.clotho.core.domain.UpgradeDependence;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @author Scott.Xie
 */
public interface UpgradeMapper {

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

    @Select("SELECT * FROM UpgradeDependence where originid=#{originid} order by upgradeid desc limit 1 ")
    @Options(useCache = true, flushCache = false)
    UpgradeDependence getdependencebyorigin(@Param("originid") int originid);

    @Select("SELECT * FROM Upgrade WHERE id=#{id}")
    @Options(useCache = true, flushCache = false)
    Upgrade getbyid(@Param("id") int id);

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
