package com.org.utils.fileupload.model;

/**
 * 文件上传目录
 * 
 * 注：key 对应properties的key
 * 
 * @author joy.zhou
 * @date 2016年1月21日
 * @version 1.0
 *
 */
public interface FileUploadDir {

	/**
	 * key
	 * 
	 * @return
	 */
	public String getKey();

	/**
	 * 上传路径
	 * 
	 * @return
	 */
	public String getPath();
}
