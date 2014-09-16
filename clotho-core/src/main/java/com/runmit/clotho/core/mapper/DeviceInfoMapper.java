package com.runmit.clotho.core.mapper;

import com.runmit.clotho.core.domain.DeviceInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *
 * @author TianLiang
 */
//@CacheNamespace(implementation = org.mybatis.caches.memcached.MemcachedCache.class)
public interface DeviceInfoMapper {

    @Insert("INSERT INTO DeviceInfo (`devicesn`,`devicetype`,`location`,`produceddate`,`status`,`createby`,`updateby`,`updatetime`) "
            + "VALUES (#{devicesn},#{devicetype},#{location},#{produceddate},#{status},#{createby},#{updateby},#{updatetime})")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "deviceid")
    void addDeviceInfo(DeviceInfo DeviceInfoEntity);
//
    @Update("UPDATE DeviceInfo SET devicesn=#{devicesn},devicetype=#{devicetype},location=#{location},produceddate=#{produceddate},status=#{status},updateby=#{updateby},updatetime=#{updatetime} where deviceid=#{deviceid}")
    @Options(flushCache = true)
    void updateDeviceInfo(DeviceInfo DeviceInfoEntity);

    @Update("UPDATE DeviceInfo SET userregcount=userregcount+1 where devicesn=#{devicesn}")
    @Options(flushCache = true)
    void addDeviceRegUser(@Param("devicesn") String devicesn);

    @Delete("DELETE FROM DeviceInfo WHERE deviceid=#{deviceid}")
    @Options(flushCache = true)
    int deleteDeviceInfo(@Param("deviceid") Integer deviceid);
//
//    @Select("SELECT * FROM UserDeviceRela WHERE  devicesn=#{devicesn}")
//    @Options(useCache = true, flushCache = false)
//    List<UserDeviceRela> getRelabyDevice(@Param("devicesn") String devicesn);

    @Select("SELECT * FROM DeviceInfo WHERE deviceid=#{deviceid}")
//    @Options(useCache = true, flushCache = false)
    DeviceInfo getbyid(@Param("deviceid") int deviceid);

    @Select("SELECT * FROM DeviceInfo WHERE devicesn=#{devicesn}")
//    @Options(useCache = true, flushCache = false)
    DeviceInfo getbysn(@Param("devicesn") String devicesn);

    @Select("SELECT * FROM DeviceInfo")
    List<DeviceInfo> getAll();

//    @Select("SELECT * FROM UserDeviceRela WHERE  id=#{id}")
//    @Options(useCache = true, flushCache = false)
//    List<UserDeviceRela> getRelabyId(@Param("id") Integer id);
}
