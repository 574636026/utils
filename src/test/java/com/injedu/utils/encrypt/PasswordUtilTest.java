package com.injedu.utils.encrypt;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * 密码服务测试
 *
 * @author joy.zhou
 * @date 2015年11月23日
 * @version 1.0
 *
 */
public class PasswordUtilTest {

	private String salt = "en4n03";

	private String password = "123456";

	private String encrptPwd = "075b719dd89cd276ee32c5acf39893e4";

	@Test
	public void testPassword() {

		Assert.assertEquals(PassWordUtils.encryptPassword(password, salt),
				encrptPwd);

	}

}
