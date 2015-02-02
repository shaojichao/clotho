package com.runmit.clotho.core.domain;

import lombok.Data;

/**
 * @author zhipeng.tian
 * 
 * @date 2015年2月2日
 */
@Data
public class CountryCode extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 182102141800723153L;
	
	private String name;
	private String countrycode;
	private String locale;
	private String language;

}
