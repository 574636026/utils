package com.org.utils.fileupload;

import com.org.utils.fileupload.model.FileEntity;
import com.org.utils.fileupload.model.FileUploadConfig;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * 文件上传工具
 *
 * @author joy.zhou
 * @date 2016年1月21日
 * @version 1.0
 *
 */
public interface IFileUpload {

	/**
	 * 上传图片
	 * 
	 * @param dir
	 *            上传的目标目录
	 * @param fileName
	 *            文件名
	 * @param size
	 *            文件大小
	 * @param is
	 *            文件流
	 * @param config
	 *            上传文件配置
	 */
	public FileEntity uploadImage(String dir, String fileName, long size, InputStream is, FileUploadConfig config);

	/**
	 * 上传文件
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
	 *            是否重名
	 * 
	 * @throws IOException
	 */
	public FileEntity upload(String dir, String fileName, long size, InputStream is, FileUploadConfig config);

}
