package com.injedu.utils.encrypt;

/**
 * 
 * 密码工具
 *
 * @author joy.zhou
 * @date 2015年11月23日
 * @version 1.0
 *
 */
public class PassWordUtils {

	/**
	 * 返回密码加密字符串
	 * 
	 * @param password
	 *            MD5密码
	 * @param salt
	 *            盐噪声
	 * @return
	 */
	public static String encryptMd5Password(String password, Object salt) {
		
		return MD5Encoder.MD5(salt + password).toLowerCase();
	}

	/**
	 * 返回密码加密字符串
	 * 
	 * @param password
	 *            明文密码
	 * @param salt
	 *            盐噪声
	 * @return
	 */
	public static String encryptPassword(String password, Object salt) {

		return encryptMd5Password(MD5Encoder.MD5(password), salt);
	}

	/**
	 * 生成盐值 规则：随机6位
	 * 
	 * @return
	 */
	public static String generateSalt() {

		return RandomUtils.radom(6);
	}

}
