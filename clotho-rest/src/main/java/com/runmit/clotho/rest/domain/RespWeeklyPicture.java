package com.runmit.clotho.rest.domain;

import lombok.Data;

/**
 * @author hongbin.cao
 *
 * @date 2015年4月7日
 */
@Data
public class RespWeeklyPicture {
	private String id;
    private String url;
    private String comment;
    private long filesize;
    private String language;
    private String linkout;
    private String bigImgUrl;
}
