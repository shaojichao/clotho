package com.runmit.clotho.core.domain.metis;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Tao.Xu on 2014/9/30.
 */
@Setter
@Getter
public class avro_schema  implements Serializable {
    private byte[] fingerprint;
    private String namespace;
    private String name;
    private String schema;
    private Integer inuse;
    private String updatetime;
    private String updateby;
    private String createtime;
}
