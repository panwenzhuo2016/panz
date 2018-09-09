/*
 * @(#)com.linker.resmaster.util.DateUtils.java
 * 南京中兴软创科技有限责任公司
 * Date:  24-Feb-2011
 */

package com.pwz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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


	/** 锁对象 */
	private static final Object lockObj = new Object();

	/** 存放不同的日期模板格式的sdf的Map */
	private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

	/**
	 * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
	 *
	 * @param pattern
	 * @return
	 */
	private static SimpleDateFormat getSdf(final String pattern) {
		ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);

		// 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
		if (tl == null) {
			synchronized (lockObj) {
				tl = sdfMap.get(pattern);
				if (tl == null) {
					// 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
					// 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
					tl = new ThreadLocal<SimpleDateFormat>() {

						@Override
						protected SimpleDateFormat initialValue() {
							return new SimpleDateFormat(pattern);
						}
					};
					sdfMap.put(pattern, tl);
				}
			}
		}

		return tl.get();
	}

	/**
	 * 是用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		return getSdf(pattern).format(date);
	}

	public static Date parse(String dateStr, String pattern) throws ParseException {
		return getSdf(pattern).parse(dateStr);
	}



}
