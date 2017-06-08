package com.runmit.clotho.rest.domain;

import com.runmit.clotho.core.domain.browser.SearchEngine;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 搜索引擎响应对象
 */
@Data
public class SearchEngineResp implements Serializable{
    //总条数
    private long total;

    //当前最新版本号
    private String versionNo;

    //搜索引擎信息列表
    private List<SearchEngine> engineList;
}
