package com.injedu.utils.encrypt;

import java.util.Random;

/**
 * 随机数工具
 *
 * @author joy.zhou
 * @date 2015年1月4日
 * @version 1.0
 *
 */
public class RandomUtils {

	private static final Random random = new Random();

	/** 随机种子 */
	private static final char codes[] = { 'a', 'c', 'd', 'e', 'f', 'g', 'i', 'h', 'm', 'n', 'x', 'y', 'z' };

	/**
	 * 生成指定长度的随机数(数字与字母)
	 * 
	 * @param length
	 *            长度
	 * @return
	 */
	public static String radom(int length) {
		StringBuilder result = new StringBuilder();
		if (length <= 0) {
			return "";
		}
		for (int i = 0; i < length; i++) {
			if (random.nextBoolean()) {
				result.append(random.nextInt(9));
			} else {
				result.append(codes[random.nextInt(codes.length)]);
			}

		}
		return result.toString();
	}

	/**
	 * 
	 * 生成指定长度的字符
	 * 
	 * @param length
	 *            长度
	 * @return
	 */
	public static String radomChar(int length) {
		StringBuilder result = new StringBuilder();
		if (length <= 0) {
			return "";
		}
		for (int i = 0; i < length; i++) {
			result.append(codes[random.nextInt(codes.length)]);
		}
		return result.toString();
	}

	/**
	 * 生成指定长度的整数
	 * 
	 * @param length
	 *            长度
	 * @return
	 */
	public static String radomInt(int length) {
		StringBuilder result = new StringBuilder();
		if (length <= 0) {
			return "";
		}
		for (int i = 0; i < length; i++) {
			result.append(random.nextInt(9));
		}
		return result.toString();
	}

	/**
	 * 生成数字范围内的整数
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return
	 */
	public static int randmt(int min, int max) {

		return (int) (random.nextDouble() * (max - min + 1) + min);
	}

}
