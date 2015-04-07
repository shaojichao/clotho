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
	
	private String url;
	private String comment;
    private long filesize;

}
