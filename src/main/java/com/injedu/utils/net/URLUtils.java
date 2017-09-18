package com.injedu.utils.net;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 统一资源定位符工具类
 *
 * @author xiaoleilu
 *
 * 
 * @date 2015年12月5日
 * @version 1.0
 *
 */
public class URLUtils {

	/**
	 * 
	 * 格式化URL链接
	 * 
	 * 
	 * @param url
	 *            需要格式化的URL
	 * 
	 * @return 格式化后的URL，如果提供了null或者空串，返回null
	 */
	public static String formatUrl(String url) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		if (url.startsWith("http://") || url.startsWith("https://")) {
			return url;
		}
		return "http://" + url;
	}

	/**
	 * 
	 * 获得path部分<br>
	 * 
	 * URI -> http://www.aaa.bbb/search?scope=ccc&q=ddd
	 * 
	 * PATH -> /search
	 * 
	 * @param uriStr
	 *            URI路径
	 * 
	 * @return path
	 * @throws URISyntaxException
	 * 
	 */
	public static String getPath(String uriStr) throws URISyntaxException {

		URI uri = new URI(uriStr);

		return uri == null ? null : uri.getPath();
	}

}
