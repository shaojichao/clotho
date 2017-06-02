package com.runmit.clotho.core.domain;

import lombok.Data;

import java.util.List;

/**
 *@author sjc on 17/5/31.
 * @param <T>
 */
@Data
public class Page<T> {
    private List<T> datas;

    private int total;

    public Page(List<T> datas, int total) {
        this.datas = datas;
        this.total = total;
    }
}
