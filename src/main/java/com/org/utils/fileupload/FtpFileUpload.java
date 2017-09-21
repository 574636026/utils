package com.org.utils.fileupload;

import com.org.utils.file.FtpUtils;
import com.org.utils.fileupload.model.FileEntity;
import com.org.utils.fileupload.model.FileUploadConfig;
import com.org.utils.log.LogUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * FTP文件上传工具类
 *
 * @author joy.zhou
 * @date 2016年1月20日
 * @version 1.0
 *
 */
public class FtpFileUpload extends AbstractFileUpload {

	/** 文件上传客户端 */
	private FtpUtils client = null;

	/**
	 * 
	 * 构造函数
	 * 
	 * @param address
	 *            FTP地址
	 * @param userName
	 *            FTP用户名
	 * @param password
	 *            FTP密码
	 */
	public FtpFileUpload(String address, String userName, String password) {

		client = new FtpUtils(address, userName, password);

	}

	/**
	 * 
	 * FTP文件上传
	 * 
	 * @param dir
	 *            上传的路径
	 * @param fileName
	 *            文件名
	 * @param is
	 *            文件流
	 * @return 访问地址
	 * @throws IOException
	 */
	@Override
	public FileEntity upload(String dir, String fileName, long size, InputStream is, FileUploadConfig config) {

		FileEntity result = new FileEntity();
		if (config.getRename()) {
			fileName = rename(fileName);
		}
		result.setName(fileName);
		result.setSize(size);
		try {

			client.upload(dir, fileName, is);

		} catch (IOException e) {
			LogUtils.e(e.getMessage(), e);
			result.setError(e.getMessage());
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				LogUtils.e(e.getMessage(), e);
				result.setError(e.getMessage());
			}
		}

		return result;
	}

}
