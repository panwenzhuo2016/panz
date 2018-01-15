/*
 * @(#)com.linker.resmaster.util.DateUtils.java
 * 南京中兴软创科技有限责任公司
 * Date:  24-Feb-2011
 */

package com.util;

import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author <a href="mailto:javawen@gmail.com">文建国 </a>
 * @version 1.0 24-Feb-2011
 */
public class DateUtils {

	public static String CURR_DATE_STR = DateUtils.getDateFormat(new Date(), "yyyyMMdd");
	/**
	 * 日期、时间格式化
	 *
	 * @param date
	 *            Date 将要被格式化的日期对象

	 *
	 *
	 * @param outFmt
	 *            String 返回样式，参照类说明，如：yyyy年MM月dd日

	 *
	 *
	 * @return String 格式化后的日期、时间字符串，data为null时返回null，outFmt非法时返回yyyyMMdd格式
	 */
	public static String getDateFormat(Date date, String outFmt) {
		if (null == date) {
			return null;
		}
		if (null == outFmt || "".equals(outFmt.trim())) { // outFmt非法
			outFmt = "yyyyMMdd";
		}

		String retu = null;
		SimpleDateFormat dateFormat = null;
		try {
			dateFormat = new SimpleDateFormat(outFmt);
		} catch (IllegalArgumentException iaex) { // outFmt非法
			dateFormat = new SimpleDateFormat("yyyyMMdd");
		}
		retu = dateFormat.format(date);

		dateFormat = null;

		return retu;
	}

}
