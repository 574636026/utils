package com.org.utils.fileupload;

import com.org.utils.encrypt.RandomUtils;
import com.org.utils.fileupload.model.FileEntity;
import com.org.utils.fileupload.model.FileUploadConfig;
import com.org.utils.image.ImageUtils;
import com.org.utils.log.LogUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * 文件上传简单实现
 *
 * @author joy.zhou
 * @date 2016年1月21日
 * @version 1.0
 *
 */
public abstract class AbstractFileUpload implements IFileUpload {

	/**
	 * 重命名文件
	 * 
	 * @param name
	 * @return
	 */
	protected String rename(String name) {
		return System.currentTimeMillis() + RandomUtils.radomInt(2) + getFileSuiff(name);
	}

	/**
	 * 获取文件后缀
	 * 
	 * @param name
	 * @return
	 */
	protected String getFileSuiff(String name) {

		String suiff = "";
		int idx = name.lastIndexOf(".");
		if (idx > -1) {
			suiff = name.substring(idx);
		}
		return suiff;
	}

	/**
	 * 
	 * @param dir
	 *            上传的目标目录
	 * @param fileName
	 *            文件名
	 * @param size
	 *            文件大小
	 * @param is
	 *            文件流
	 * @param isRename
	 *            是否重命名
	 * @param isCompress
	 *            是否压缩
	 * @return
	 */
	public FileEntity uploadImage(String dir, String fileName, long size, InputStream is, FileUploadConfig config) {
		String type = getFileSuiff(fileName);

		String error = null;

		try {
			// 判断是否压缩
			if (config.getCompress() && size > config.getCompressLimit()) {
				ImageUtils utils;
				utils = new ImageUtils(is, type, true);
				is = utils.transfer();
			}
		} catch (IOException e) {
			LogUtils.e(e.getMessage(), e);
			error = e.getMessage();
		}

		FileEntity result = this.upload(dir, fileName, size, is, config);

		if (error != null) {
			result.setError(error);
		}

		return result;

	}

}
