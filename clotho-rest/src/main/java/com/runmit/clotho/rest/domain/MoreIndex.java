package com.runmit.clotho.rest.domain;

import lombok.Data;

/**
 * Created by qian.liu on 2016/4/5.
 */
@Data
public class MoreIndex {
    private String name;//指数指标1名称
    private String code;//指标编码
    private String index;//等级
    private String details;//描述
    private String otherName;//其他信息
}
