package com.org.utils.fileupload.model;

import java.io.Serializable;

/**
 * 
 * 上传文件实体
 *
 * @author joy.zhou
 * @date 2015年12月19日
 * @version 1.0
 *
 */
public class FileEntity implements Serializable {

	private static final long serialVersionUID = 7020223880779820139L;

	/** 文件名 */
	private String name;
	/** 文件大小 */
	private long size;
	/** 访问路径 */
	private String url;
	/** 错误信息 */
	private String error = "";

	public FileEntity() {
	}

	public FileEntity(String name, long size, String url) {
		this.name = name;
		this.size = size;
		this.url = url;
	}

	public FileEntity(String name, long size, String url, String error) {
		this(name, size, url);
		this.error = error;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "UploadFile [name=" + name + ", size=" + size + ", url=" + url + ", error=" + error + "]";
	}

}
