package com.injedu.utils.encrypt;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.injedu.utils.log.LogUtils;

/**
 * AES 解密
 * 
 * @author joy.zhou
 * @date 2017年1月11日
 * @version 1.0
 */
public class AES {

	private static final String CHARSET = "UTF-8";

	public static String decrypt(String content, String key, String iv) {

		try {

			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

			IvParameterSpec ivSpec = new IvParameterSpec(Base64.decodeBase64(iv));

			SecretKeySpec keySpec = new SecretKeySpec(Base64.decodeBase64(key), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

			byte[] result = cipher.doFinal(Base64.decodeBase64(content));

			return new String(result, CHARSET);

		} catch (Exception e) {
			LogUtils.e(e.getMessage(), e);
		}

		return null;
	}

}
