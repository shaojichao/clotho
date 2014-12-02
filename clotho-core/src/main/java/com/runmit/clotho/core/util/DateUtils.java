package com.runmit.clotho.core.util;

import java.text.ParseException;
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
	
	public static Date parseDateTime(String date) throws Exception{
		return org.apache.commons.lang.time.DateUtils.parseDate(date, new String[]{"yyyy-MM-dd HH:mm:ss"});
	}
	
}
