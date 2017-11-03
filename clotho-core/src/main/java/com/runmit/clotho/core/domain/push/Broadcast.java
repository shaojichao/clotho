package com.runmit.clotho.core.domain.push;

import lombok.Data;

import java.io.Serializable;

/**
 * 推送对象
 * @author sjc
 * @date 2017-10-09-17:27
 */
@Data
public class Broadcast implements Serializable {

    /**
     * 推送内容
     */
    private String content;

    /**
     * true是离线消息，false不是离线消息
     */
    private boolean offline;

    /**
     * 必须，应用app的唯一标识
     */
    private String appID;

    /**
     * String数组，非必须，查询条件的key和value的顺序数组
     例如：[key1,value1,key2,value2…]
     */
   // private String keyValues;

}
