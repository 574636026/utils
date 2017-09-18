package com.injedu.utils.fileupload.model;

/**
 * 上传配置
 *
 * @author joy.zhou
 * @date 2016年8月22日
 * @version 1.0
 *
 */
public class FileUploadConfig {

	/** 是否改名 */
	private boolean rename = true;
	/** 是否压缩图片 */
	private boolean compress = true;
	/** 图片压缩最大限制(byte) */
	private long compressLimit = 524288l;

	public FileUploadConfig() {
	}

	public FileUploadConfig(boolean rename) {
		this.rename = rename;
	}

	public FileUploadConfig(boolean rename, boolean compress) {
		this.rename = rename;
		this.compress = compress;
	}

	public FileUploadConfig(boolean compress, long compressLimit) {
		this.compress = compress;
		this.compressLimit = compressLimit;
	}

	public FileUploadConfig(boolean rename, boolean compress, long compressLimit) {
		this.rename = rename;
		this.compress = compress;
		this.compressLimit = compressLimit;
	}

	public boolean getRename() {
		return rename;
	}

	public void setRename(boolean rename) {
		this.rename = rename;
	}

	public boolean getCompress() {
		return compress;
	}

	public void setCompress(boolean compress) {
		this.compress = compress;
	}

	public long getCompressLimit() {
		return compressLimit;
	}

	public void setCompressLimit(long compressLimit) {
		this.compressLimit = compressLimit;
	}

}
