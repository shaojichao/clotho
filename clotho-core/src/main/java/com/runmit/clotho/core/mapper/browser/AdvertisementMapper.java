package com.runmit.clotho.core.mapper.browser;

import com.runmit.clotho.core.domain.browser.Advertisement;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author sjc
 * @date 2017-06-01-10:33
 */
public interface AdvertisementMapper {

    /**
     * 分页查出所有开屏广告信息
     * @param start
     * @param limit
     * @return
     */
    @Select({"<script>",
            "SELECT a.*,p.model ",
            "FROM Ad a ",
            "LEFT JOIN PhoneModel p ON p.id=a.modeId ",
            "ORDER BY createTime DESC ",
            "LIMIT #{start},#{limit} ",
            "</script>"})
    @Options(useCache = true, flushCache = false)
    List<Advertisement> getAdvertisementList(@Param("start") int start, @Param("limit") int limit);

    /**
     * 查出开屏广告的条数
     * @return
     */
    @Select({"<script>",
            "SELECT count(1) ",
            "FROM Ad a ",
            "LEFT JOIN PhoneModel p ON p.id=a.modeId ",
            "</script>"})
    @Options(useCache = true, flushCache = false)
    int getAdvertisementCount();

    /**
     * 新增开屏广告信息
     * @param advertisement
     */
    @Insert("INSERT INTO Ad(modeId,adURL,status,version,createBy,createTime,updateBy,updateTime) " +
            "VALUES(#{modeId},#{adURL},#{status},#{version},#{createBy},now(),#{updateBy},now())")
    @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
    void addAdvertisement(Advertisement advertisement);

    /**
     * 修改开屏广告信息
     * @param advertisement
     */
    @Update("UPDATE Ad " +
            "SET status=#{status},updateTime=now(),updateBy=#{updateBy} " +
            "where id=#{id}")
    @Options(flushCache = true)
    void updateAdvertisement(Advertisement advertisement);

    /**
     * 根据id删除对应开屏广告信息
     * @param id
     * @return
     */
    @Delete("DELETE FROM Ad WHERE id=#{id}")
    @Options(flushCache = true)
    int deleteAdvertisementById(@Param("id") Integer id);

}
