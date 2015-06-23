package com.runmit.clotho.core.domain.picture;

import com.runmit.clotho.core.domain.BaseDomain;
import lombok.Data;

/**
 * @author hongbin.cao
 * 
 * @date 2015年4月7日
 */
@Data
public class WeeklyPicture extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 182102141800723153L;

    /*首页广告图地址*/
	private String url;
    /*备注说明*/
	private String comment;
    /*图片大小*/
    private long filesize;
    /*图片类型：首页广告图:1; 广告大图:2*/
    private String type;
    /*语言类型*/
    private String language;
    /*图片外链地址*/
    private String linkout;
    /*广告大图地址*/
    private String bigImgUrl;

}
