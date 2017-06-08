package com.runmit.clotho.rest.domain;

import com.runmit.clotho.core.domain.browser.Navigation;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 导航栏响应对象
 */
@Data
public class NavigationResp implements Serializable{
    //总条数
    private long total;

    //当前最新版本号
    private String versionNo;

    //导航栏信息列表
    private List<Navigation> navList;
}
