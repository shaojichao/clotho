package com.runmit.clotho.core.mapper;

import com.runmit.clotho.core.domain.UserFeedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 *
 * @author Scott.Xie
 */
public interface UserFeedbackMapper {

    @Insert("INSERT INTO UserFeedback (`hwid`,`udid`,`wifimac`,`wirelesssmac`,`wiremac`,`os`,`osver`,`device`,`area`,`language`,`imei`,`idfv`,`appkey`,`appver`,`uid`,`devicebrand`,`devicedevice`,`devicemodel`,`devicehardware`,`deviceid`,`deviceserial`,`ro`,`channel`,`dts`,`contact`,`content`) "
            + "VALUES (#{hwid},#{udid},#{wifimac},#{wirelesssmac},#{wiremac},#{os},#{osver},#{device},#{area},#{language},#{imei},#{idfv},#{appkey},#{appver},#{uid},#{devicebrand},#{devicedevice},#{devicemodel},#{devicehardware},#{deviceid},#{deviceserial},#{ro},#{channel},#{dts},#{contact},#{content})")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addUserFeedback(UserFeedback userFeedback);
}
