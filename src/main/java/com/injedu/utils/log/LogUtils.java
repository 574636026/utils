package com.injedu.utils.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 日志工具类
 *
 * @author joy.zhou
 * @date 2016年1月8日
 * @version 1.0
 *
 */
public class LogUtils {

	private static Logger logger = LoggerFactory.getLogger(LogUtils.class);

	/**
	 * debug
	 * 
	 * @param info
	 */
	public static void d(String info) {
		d(info,null);
	}

	/**
	 * debug
	 * 
	 * @param info
	 * @param t
	 */
	public static void d(String info, Throwable t) {
		logger.debug(info, t);
	}

	/**
	 * info
	 * 
	 * @param info
	 * @param t
	 */
	public static void i(String info) {
		i(info,null);
	}

	/**
	 * info
	 * 
	 * @param info
	 * @param t
	 */
	public static void i(String info, Throwable t) {
		logger.info(info, t);
	}

	/**
	 * warn
	 * 
	 * @param info
	 * @param t
	 */
	public static void w(String info) {
		w(info,null);
	}

	/**
	 * warn
	 * 
	 * @param info
	 * @param t
	 */
	public static void w(String info, Throwable t) {
		logger.warn(info, t);
	}

	/**
	 * error
	 * 
	 * @param info
	 * @param t
	 */
	public static void e(String info) {
		e(info,null);
	}

	/**
	 * error
	 * 
	 * @param info
	 * @param t
	 */
	public static void e(String info, Throwable t) {
		logger.error(info, t);
	}
}
