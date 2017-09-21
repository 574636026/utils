package com.org.utils.enums;

/**
 * 
 *
 * @author joy.zhou
 * @date 2016年2月2日
 * @version 1.0
 *
 */
public class EnumDetailUtils {

	/**
	 * 
	 * @param cls
	 *            泛型
	 * @param type
	 *            类型
	 * @return
	 */
	public static <T extends EnumDetail> T valueOf(Class<T> cls, int type) {

		T[] cts = cls.getEnumConstants();

		for (T t : cts) {
			if (type == t.getType()) {
				return t;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param cls
	 *            泛型
     * @param name
     *            类型
	 * @return
	 */
	public static <T extends EnumDetail> T nameOf(Class<T> cls, String name) {

		T[] cts = cls.getEnumConstants();

		for (T t : cts) {
			if (name.equals(t.getName())) {
				return t;
			}
		}
		return null;
	}

}
