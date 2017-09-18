package com.injedu.utils.tools;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Range;

/**
 * 字符串操作工具包
 * 
 */
public class ConvertUtils {

	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 字符串转布尔值
	 * 
	 * @param b
	 * @return 转换异常返回 false
	 */
	public static boolean toBool(String b) {
		try {
			return Boolean.parseBoolean(b);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 分隔并转化
	 * 
	 * @param str
	 *            字符串
	 * @param separatorChar
	 *            分隔符
	 * @return
	 */
	public static Integer[] splitToInt(String str, String separatorChar) {

		if (StringUtils.isBlank(str)) {
			return null;
		}

		String[] arr = StringUtils.split(str, separatorChar);

		Integer[] result = new Integer[arr.length];

		for (int i = 0; i < arr.length; i++) {
			result[i] = toInt(arr[i]);
		}
		return result;
	}

	/**
	 * 分隔并转化
	 * 
	 * @param str
	 *            字符串
	 * @param separatorChar
	 *            分隔符
	 * @return
	 */
	public static Long[] splitToLong(String str, String separatorChar) {

		if (StringUtils.isBlank(str)) {
			return null;
		}

		String[] arr = StringUtils.split(str, separatorChar);

		Long[] result = new Long[arr.length];

		for (int i = 0; i < arr.length; i++) {
			result[i] = toLong(arr[i]);
		}
		return result;
	}

	/**
	 * 分隔并转化
	 * 
	 * @param str
	 *            字符串
	 * @param separatorChar
	 *            分隔符
	 * @param @rangeChar
	 *            区间分隔符
	 * @return
	 */
	public static List<Range<Integer>> splitToRangeList(String str, String separatorChar, String rangeChar) {

		if (StringUtils.isBlank(str)) {
			return null;
		}

		List<Range<Integer>> list = new ArrayList<>();

		String[] arr = StringUtils.split(str, separatorChar);

		for (String temp : arr) {
			Range<Integer> range = splitToRange(temp, rangeChar);
			if (range != null) {
				list.add(range);
			}
		}

		return list;
	}

	/**
	 * 分隔并转化
	 * 
	 * @param str
	 *            字符串
	 * @param separatorChar
	 *            分隔符
	 * @return
	 */
	public static Range<Integer> splitToRange(String str, String separatorChar) {

		Range<Integer> range = null;

		if (StringUtils.isBlank(str)) {
			return range;
		}

		if (str.contains(separatorChar)) {
			String[] arr = StringUtils.split(str, separatorChar);
			range = Range.closed(toInt(arr[0]), toInt(arr[1]));
		} else {
			range = Range.singleton(toInt(str));
		}

		return range;

	}

	/**
	 * 格式化textarea内容
	 * 
	 * 换行符替换为<br/>
	 * 
	 * @param text
	 * @return
	 */
	public static String formatTextarea(String text) {

		if (StringUtils.isBlank(text)) {
			return text;
		}
		return text.replaceAll("\n|\r\n", "<br/>");
	}

	/**
	 * 金额转换,分 --> 元(保留两位精度,四舍五入)
	 * 
	 * @param money
	 * @return
	 */
	public static BigDecimal toYuan(int money) {

		return new BigDecimal(money).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 金额转换,元--分
	 * 
	 * @param money
	 * @return
	 */
	public static int toFen(BigDecimal money) {
		return money.multiply(new BigDecimal(100)).intValue();
	}

	/**
	 * 金额计算(保留两位小数,忽略其他位)
	 * 
	 * @param amount
	 *            价格
	 * @param discount
	 *            折扣
	 * @return
	 */
	public static BigDecimal toMoney(BigDecimal amount, BigDecimal discount) {
		BigDecimal paidAmount = amount.multiply(discount);
		return paidAmount.setScale(2, BigDecimal.ROUND_UP);
	}

	/**
	 * 金额计算(保留整数位)
	 * 
	 * @param amount
	 *            价格
	 * @param discount
	 *            折扣
	 * @return
	 */
	public static BigDecimal toInteger(BigDecimal amount, BigDecimal discount) {
		BigDecimal paidAmount = amount.multiply(discount);
		return paidAmount.setScale(0, BigDecimal.ROUND_UP);
	}

	/**
	 * 金额计算(保留两位小数,忽略其他位)
	 * 
	 * @param amount
	 *            价格
	 * @param discount
	 *            折扣
	 * @return
	 */
	public static BigDecimal toMoney(Integer amount, BigDecimal discount) {
		BigDecimal paidAmount = new BigDecimal(amount).multiply(discount);
		return paidAmount.setScale(2, BigDecimal.ROUND_UP);
	}

}
