package com.runmit.clotho.rest.domain;

import com.runmit.clotho.core.domain.browser.Advertisement;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 开屏广告响应对象
 */
@Data
public class AdvertisementResp implements Serializable{
    //分辨率宽
    private int width;
    //分辨率高
    private int height;

    //开屏广告信息列表
    private List<Advertisement> adList;
}
