package com.org.utils.supports;

/**
 * 第三方应用key
 *
 * @author joy.zhou
 * @date 2016年8月23日
 * @version 1.0
 *
 */
public class SecurityKey {

	/** 应用key */
	private String appkey;
	/** 密钥 */
	private String secret;

	public SecurityKey() {
	}

	public SecurityKey(String appkey, String secret) {
		this.appkey = appkey;
		this.secret = secret;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}
