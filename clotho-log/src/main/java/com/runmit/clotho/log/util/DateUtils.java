package com.runmit.clotho.log.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月28日
 */

public class DateUtils {
	public static String getDateString(Date date, String format) {
		if (date == null) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String getDateTime(Date date){
		return getDateString(date, "yyyy-MM-dd HH:mm:ss");
	}
}
