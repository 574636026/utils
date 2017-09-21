package com.org.utils.net;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 
 * HttpRequest工具类
 *
 * @author joy.zhou
 * @date 2015年11月12日
 * @version 1.0
 *
 */
public class RequestUtils {

	/**
	 * 判断Http请求是否为ajax
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request) {

		if (StringUtils.isBlank(request.getHeader("Accept"))) {
			return false;
		}
		return (request.getHeader("Accept").indexOf("application/json") > -1
				|| (request.getHeader("X-Requested-With") != null
						&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1));
	}

	/**
	 * 得到项目地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getProjectUrl(HttpServletRequest request) {

		String url = request.getScheme() + "://" + request.getServerName();

		if (request.getServerPort() != 80) {
			url += ":" + request.getServerPort();
		}

		url += request.getContextPath();

		return url;
	}

	public static String resolveRequest(HttpServletRequest request) {

		StringBuilder sb = new StringBuilder("Request:");

		sb.append(request.getRequestURI()).append("\r\n");

		Enumeration<String> headers = request.getHeaderNames();

		if (headers != null) {

			sb.append("Header:[");

			while (headers.hasMoreElements()) {

				String name = headers.nextElement();

				sb.append(name).append(" - ").append(toEnumString(request.getHeaders(name))).append(",");

			}

		}

		sb.append("]");

		return sb.toString();
	}

	private static String toEnumString(Enumeration<String> enums) {

		if (enums == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		while (enums.hasMoreElements()) {
			sb.append("|").append(enums.nextElement());
		}

		if (sb.length() > 0) {
			sb.deleteCharAt(0);
		}

		return sb.toString();
	}

}
