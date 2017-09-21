package com.org.utils.date;

import com.org.utils.log.LogUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 日期工具类
 *
 * @date 2015年11月9日
 * @version 1.0
 *
 */
public class DateUtils {

	/** 其他日期格式请使用lang3中的timeapi(DateFormatUtils类) */
	public static final FastDateFormat DATE_TIME_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
	public static final FastDateFormat DATE_TIME_LANG_FORMAT = FastDateFormat.getInstance("yyyyMMddHHmmss");

	private static final String[] WEEKDAYS = new String[] { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	/**
	 * 获取当前星期值
	 * 
	 * @return
	 */
	public static Integer getCurrentWeekDay() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 字符串转换到时间格式
	 * 
	 * @param dateStr
	 *            需要转换的字符串
	 * @param formatStr
	 *            需要格式的目标字符串 举例 yyyy-MM-dd
	 * @return Date 返回转换后的时间
	 * @throws ParseException
	 *             转换异常
	 */
	public static Date StringToDate(String dateStr, String formatStr) throws ParseException {

		return FastDateFormat.getInstance(formatStr).parse(dateStr);
	}

	/**
	 * 
	 * 时间格式转为字符串
	 * 
	 * @param date
	 *            需要转换日期
	 * @param formatStr
	 *            格式化字符串 举例 yyyy-MM-dd
	 * @return
	 */
	public static String dateToString(Date date, String formatStr) {
		return FastDateFormat.getInstance(formatStr).format(date);
	}

	/**
	 * 
	 * 时间格式转为字符串(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param date
	 *            需要转换日期
	 * @return
	 */
	public static String dateToString(Date date) {
		return DATE_TIME_FORMAT.format(date);
	}

	/**
	 * 判断当前日期是否在起始日期与结束日期之间
	 * 
	 * @param beginDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @return
	 * @throws ParseException
	 */
	public static boolean isBetweenDate(String beginDate, String endDate) {

		Date now = nowDate();
		Date begin = null;
		Date end = null;
		try {
			begin = DateFormatUtils.ISO_DATE_FORMAT.parse(beginDate);

			end = DateFormatUtils.ISO_DATE_FORMAT.parse(endDate);

			return now.compareTo(begin) >= 0 && now.compareTo(end) <= 0;

		} catch (ParseException e) {
			LogUtils.e(e.getMessage(), e);
		}

		return false;
	}

	/**
	 * 距离当前多少天
	 * 
	 * @param dateStr
	 *            日期
	 * @return
	 */
	public static int diffCurrentDay(String dateStr) {

		try {

			Date now = nowDate();
			Date date = DateFormatUtils.ISO_DATE_FORMAT.parse(dateStr);
			return (int) ((now.getTime() - date.getTime()) / (24 * 60 * 60 * 1000) + 0.5);

		} catch (ParseException e) {
			LogUtils.e(e.getMessage(), e);
		}

		return 0;
	}

	/**
	 * 计算日期间隔
	 * 
	 * @param beginTime
	 *            起始时间
	 * @param endTime
	 *            结束时间
	 * @param field
	 *            间隔类型
	 * @return
	 */
	public static int diff(Calendar beginTime, Calendar endTime, int field) {

		return beginTime.get(field) - endTime.get(field);
	}

	/**
	 * 与当前时间间隔
	 * 
	 * @param time
	 *            比较时间
	 * @param field
	 *            比较字段
	 * @return
	 */
	public static int diffCurrent(String time, int field) {
		Calendar createDate = Calendar.getInstance();
		try {
			createDate.setTime(DateUtils.DATE_TIME_FORMAT.parse(time));
			return diff(createDate, Calendar.getInstance(), field);
		} catch (ParseException e) {
			LogUtils.e(e.getMessage(), e);
		}
		return -1;
	}

	/**
	 * 格式化unix timestamp
	 * 
	 * @param unixTimestamp
	 * @return
	 */
	public static String formatUnixTime(long unixTimestamp) {

		return DATE_TIME_FORMAT.format(new Date(unixTimestamp * 1000));

	}

	/**
	 * 得到当前时间
	 * 
	 * @return
	 */
	public static Date now() {

		return Calendar.getInstance().getTime();
	}

	/**
	 * 得到当前日期(时间置为0)
	 * 
	 * @return
	 */
	public static Date nowDate() {

		Calendar timeNow = Calendar.getInstance();
		timeNow.set(Calendar.HOUR_OF_DAY, 0);
		timeNow.set(Calendar.MINUTE, 0);
		timeNow.set(Calendar.SECOND, 0);
		timeNow.set(Calendar.MILLISECOND, 0);

		return timeNow.getTime();
	}

	/**
	 * 得到当前时间 YYYY-MM-DD HH:mm:ss
	 * 
	 * @return
	 */
	public static String nowTime() {

		return DATE_TIME_FORMAT.format(Calendar.getInstance());
	}

	/**
	 * 得到当前日期 YYYY-MM-DD
	 * 
	 * @return
	 */
	public static String nowDateString() {

		return DateFormatUtils.ISO_DATE_FORMAT.format(Calendar.getInstance());
	}

	/**
	 * 是否在日期之间
	 * 
	 * @param timeStart
	 *            开始日期
	 * @param timeEnd
	 *            结束日期
	 * @return
	 */
	public static boolean isBetween(Calendar timeStart, Calendar timeEnd) {

		Calendar timeNow = Calendar.getInstance();

		return timeNow.before(timeEnd) && timeNow.after(timeStart);
	}

	/**
	 * 是否是当前日期
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isCurrentDate(String date) {

		return nowDate().equals(date);
	}

	/**
	 * 获取星期(中午)
	 * 
	 * @param weekday
	 *            参考Calender星期
	 * @return
	 */
	public static String getWeekDayFormat(Integer weekday) {

		return WEEKDAYS[weekday - 1];
	}

	/**
	 * 友好的方式显示时间
	 */
	public static String friendlyFormat(Date date) {
		if (date == null)
			return "";
		Calendar now = Calendar.getInstance();
		String time = dateToString(date, "HH:mm");

		// 第一种情况，日期在同一天
		String curDate = DateFormatUtils.ISO_DATE_FORMAT.format(now.getTime());
		String paramDate = DateFormatUtils.ISO_DATE_FORMAT.format(date);

		if (curDate.equals(paramDate)) {
			int hour = (int) ((now.getTimeInMillis() - date.getTime()) / 3600000);
			if (hour > 0)
				return time;
			int minute = (int) ((now.getTimeInMillis() - date.getTime()) / 60000);
			if (minute < 2)
				return "刚刚";
			if (minute > 30)
				return "半个小时以前";
			return minute + "分钟前";
		}

		// 第二种情况，不在同一天
		int days = (int) ((getBegin(curDate).getTime() - getBegin(paramDate).getTime()) / 86400000);
		if (days == 1)
			return "昨天 " + time;
		if (days == 2)
			return "前天 " + time;
		if (days <= 7)
			return days + "天前";
		return dateToString(date);
	}

	/**
	 * 返回日期的0点:2012-07-07 20:20:20 --> 2012-07-07 00:00:00
	 */
	public static Date getBegin(String date) {
		try {
			return DATE_TIME_FORMAT.parse(date + " 00:00:00");
		} catch (ParseException e) {
			LogUtils.e(e.getMessage(), e);
		}

		return null;
	}

}
