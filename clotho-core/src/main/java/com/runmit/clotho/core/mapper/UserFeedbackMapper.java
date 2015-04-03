package com.runmit.clotho.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.runmit.clotho.core.domain.userfeedback.UserFeedback;

/**
 *
 * @author Scott.Xie
 */
public interface UserFeedbackMapper {

    @Insert("INSERT INTO UserFeedback (`hwid`,`udid`,`clientId`,`wifimac`,`wirelesssmac`,`wiremac`,`os`,`osver`,`device`,`area`,`language`,`imei`,`idfv`,`appkey`,`appver`,`uid`,`devicebrand`,`devicedevice`,`devicemodel`,`devicehardware`,`deviceid`,`deviceserial`,`ro`,`channel`,`dts`,`contact`,`content`) "
            + "VALUES (#{hwid},#{udid},#{clientId},#{wifimac},#{wirelesssmac},#{wiremac},#{os},#{osver},#{device},#{area},#{language},#{imei},#{idfv},#{appkey},#{appver},#{uid},#{devicebrand},#{devicedevice},#{devicemodel},#{devicehardware},#{deviceid},#{deviceserial},#{ro},#{channel},#{dts},#{contact},#{content})")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addUserFeedback(UserFeedback userFeedback);
    
    @Select("SELECT uf.*,cli.name as clientname FROM UserFeedback uf " +
            "left join Client cli on uf.clientId = cli.clientId " +
            "where uf.clientId=#{clientId} ORDER BY uf.id DESC LIMIT #{start},#{limit}" )
    List<UserFeedback> getList(@Param("clientId") int clientId,@Param("start")int start,@Param("limit")int limit);
    
    @Select("SELECT count(*) FROM UserFeedback WHERE clientId=#{clientId}")
    long getCount(@Param("clientId") int clientId);
}
