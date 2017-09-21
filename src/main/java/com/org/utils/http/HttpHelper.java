package com.org.utils.http;

import com.org.utils.log.LogUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

/**
 * HTTP工具类，封装HttpClient4.3.x来对外提供简化的HTTP请求
 * 
 * @Date Aug 5, 2014
 */
public class HttpHelper {

	private static Integer socketTimeout = 9000;
	private static Integer connectTimeout = 9000;
	private static Integer connectionRequestTimeout = 9000;

	/**
	 * 使用Get方式 根据URL地址，获取ResponseContent对象
	 * 
	 * @param url
	 *            完整的URL地址
	 * @return ResponseContent 如果发生异常则返回null，否则返回ResponseContent对象
	 */
	public static ResponseContent getUrlRespContent(String url) {
		HttpClientWrapper hw = new HttpClientWrapper(connectionRequestTimeout, connectTimeout, socketTimeout);
		ResponseContent response = null;
		try {
			response = hw.getResponse(url);
		} catch (Exception e) {
			LogUtils.e(e.getMessage(),e);
		}
		return response;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static InputStream getUrlResp(String url) {

		HttpClientWrapper hw = new HttpClientWrapper(connectionRequestTimeout, connectTimeout, socketTimeout);
		InputStream in = null;
		try {
			in = hw.getResponseInput(url).getContent();
		} catch (Exception e) {
			LogUtils.e(e.getMessage(),e);
		}
		return in;
	}

	/**
	 * 使用Get方式 根据URL地址，获取ResponseContent对象
	 * 
	 * @param url
	 *            完整的URL地址
	 * @param urlEncoding
	 *            编码，可以为null
	 * @return ResponseContent 如果发生异常则返回null，否则返回ResponseContent对象
	 */
	public static ResponseContent getUrlRespContent(String url, String urlEncoding) {
		HttpClientWrapper hw = new HttpClientWrapper(connectionRequestTimeout, connectTimeout, socketTimeout);
		ResponseContent response = null;
		try {
			response = hw.getResponse(url, urlEncoding);
		} catch (Exception e) {
			LogUtils.e(e.getMessage(),e);
		}
		return response;
	}

	/**
	 * 将参数拼装在url中，进行post请求。
	 * 
	 * @param url
	 * @return
	 */
	public static ResponseContent postUrlRespContent(String url) {
		HttpClientWrapper hw = new HttpClientWrapper();
		ResponseContent ret = null;
		try {
			setParams(url, hw);
			ret = hw.postNV(url);
		} catch (Exception e) {
			LogUtils.e(e.getMessage(),e);
		}
		return ret;
	}

	/**
	 * post请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static ResponseContent postUrlRespContent(String url, Map<String, String> params) {
		HttpClientWrapper hw = new HttpClientWrapper();
		ResponseContent ret = null;
		try {

			if (params != null && params.size() > 0) {
				for (String key : params.keySet()) {
					hw.addNV(key, params.get(key));
				}
			}
			ret = hw.postNV(url);
		} catch (Exception e) {
			LogUtils.e(e.getMessage(),e);
		}
		return ret;
	}

	private static void setParams(String url, HttpClientWrapper hw) {
		String[] paramStr = url.split("[?]", 2);
		if (paramStr == null || paramStr.length != 2) {
			return;
		}
		String[] paramArray = paramStr[1].split("[&]");
		if (paramArray == null) {
			return;
		}
		for (String param : paramArray) {
			if (param == null || "".equals(param.trim())) {
				continue;
			}
			String[] keyValue = param.split("[=]", 2);
			if (keyValue == null || keyValue.length != 2) {
				continue;
			}
			hw.addNV(keyValue[0], keyValue[1]);
		}
	}

	/**
	 * post 请求（包括图片）
	 * 
	 * @param url
	 *            请求URL
	 * @param paramsMap
	 *            参数和值
	 * @return
	 */
	public static ResponseContent postEntity(String url, Map<String, Object> paramsMap) {
		HttpClientWrapper hw = new HttpClientWrapper();
		ResponseContent ret = null;
		try {
			setParams(url, hw);
			Iterator<String> iterator = paramsMap.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				Object value = paramsMap.get(key);
				if (value instanceof File) {
					FileBody fileBody = new FileBody((File) value);
					hw.addContentBodies(key, fileBody);
				} else if (value instanceof byte[]) {
					byte[] byteVlue = (byte[]) value;
					ByteArrayBody byteArrayBody = new ByteArrayBody(byteVlue, key);
					hw.addContentBodies(key, byteArrayBody);
				} else {
					if (value != null && !"".equals(value)) {
						hw.addNV(key, String.valueOf(value));
					} else {
						hw.addNV(key, "");
					}
				}
			}
			ret = hw.postEntity(url);
		} catch (Exception e) {
			LogUtils.e(e.getMessage(),e);
		}
		return ret;
	}

	/**
	 * 使用post方式，发布对象转成的json给Rest服务。
	 * 
	 * @param url
	 * @param jsonBody
	 * @return
	 */
	public static ResponseContent postJsonEntity(String url, String jsonBody) {
		return postEntity(url, jsonBody, ContentType.APPLICATION_JSON);
	}

	/**
	 * 使用post方式，发布对象转成的xml给Rest服务
	 * 
	 * @param url
	 *            URL地址
	 * @param xmlBody
	 *            xml文本字符串
	 * @return ResponseContent 如果发生异常则返回空，否则返回ResponseContent对象
	 */
	public static ResponseContent postXmlEntity(String url, String xmlBody) {
		return postEntity(url, xmlBody, ContentType.APPLICATION_XML);
	}

	private static ResponseContent postEntity(String url, final String body, ContentType contentType) {
		HttpClientWrapper hw = new HttpClientWrapper();
		ResponseContent ret = null;
		try {
			ret = hw.postBody(url, body);
		} catch (Exception e) {
			LogUtils.e(e.getMessage(),e);
		}
		return ret;
	}

	/**
	 * 
	 * @param p_url
	 * @param params
	 * @return
	 */
	public static String makeURL(String p_url, Map<String, String> params) {
		StringBuilder url = new StringBuilder(p_url);

		if (params != null && !params.isEmpty()) {
			if (url.indexOf("?") < 0)
				url.append('?');

			for (String name : params.keySet()) {
				url.append('&');
				url.append(name);
				url.append('=');
				url.append(params.get(name));
			}
		}

		return url.toString().replace("?&", "?");
	}

}
