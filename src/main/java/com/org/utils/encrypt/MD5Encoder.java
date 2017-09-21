package com.org.utils.encrypt;

import java.security.MessageDigest;

/**
 * 
 *
 * @date 2015年11月16日
 * @version 1.0
 *
 */
public class MD5Encoder {

	/**
	 * MDT加密,32位
	 * 
	 * @param inStr
	 * @return
	 */
	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		byte[] md5Bytes = md5.digest(inStr.getBytes());

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

}
