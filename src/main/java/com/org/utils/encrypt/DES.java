package com.org.utils.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/**
 * 
 *
 * @date 2015年11月16日
 * @version 1.0
 *
 */
public class DES {

	private static final String CHARSET = "UTF-8";
	private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };

	/**
	 * DES 加密
	 * 
	 * @param encryptString
	 * @return
	 * @throws Exception
	 */
	public static String encryptDES(String encryptString,String encryptKey)
			throws GeneralSecurityException, UnsupportedEncodingException {
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
		byte[] encryptedData = cipher.doFinal(encryptString.getBytes(CHARSET));
		return MyBase64.encode(encryptedData);
	}

	/**
	 * DES 解密
	 * 
	 * @param decryptString
	 * @return
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */
	public static String decryptDES(String decryptString,String encryptKey)
			throws GeneralSecurityException, UnsupportedEncodingException {

		byte[] byteMi = MyBase64.decode(decryptString);
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		// IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte decryptedData[] = cipher.doFinal(byteMi);
		return new String(decryptedData, CHARSET);
	}
}
