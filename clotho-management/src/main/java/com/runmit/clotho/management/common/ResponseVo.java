package com.runmit.clotho.management.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 对比数据。
 *
 * @author <a href="mailto:Scott.Xie@Runmit.com">XP</a>
 * @date 2014-8-14
 */
public class ResponseVo<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2705441863715668339L;

    /**
     * table data
     */
    @Setter
    @Getter
    private Collection<T> datas;



    /**
     *
     */
    public ResponseVo() {
    }

    public ResponseVo(Collection<T> datas) {
        this.datas = datas;
    }

    public boolean isNone() {
        return this.datas.isEmpty();
    }


}
