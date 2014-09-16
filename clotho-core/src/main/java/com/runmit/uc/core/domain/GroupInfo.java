package com.runmit.uc.core.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @author TianLiang
 */
@Setter
@Getter
public class GroupInfo implements Serializable {

    private Integer groupid;
    private String groupname;
    private String groupdes;
    private String location;
    private String status;
    private String createby;
    private String createtime;
    private String updateby;
    private String updatetime;

}
