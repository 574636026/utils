package com.org.utils.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * FTP文件处理工具类
 * 
 */
public class FtpUtils {

	/** 默认缓冲大小 */
	private int BUFF_SIZE = 1024;
	/** 默认端口号 */
	private int DEFAULT_PORT = 21;

	/** 服务器地址 */
	private String address;
	/** 用户名 */
	private String userName;
	/** 密码 */
	private String password;

	/**
	 * 构造方法
	 */
	public FtpUtils() {

	}

	/**
	 * 构造方法
	 * 
	 * @param address
	 *            地址
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 */
	public FtpUtils(String address, String userName, String password) {
		this.address = address;
		this.userName = userName;
		this.password = password;
	}

	/**
	 * 获取FTP客户端对象
	 * 
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	protected FTPClient getClient() throws IOException {
		FTPClient client = new FTPClient();
		client.connect(this.address, DEFAULT_PORT);
		client.login(this.userName, this.password);
		// client.setCharset(Charset.forName("utf-8"));
		client.setBufferSize(BUFF_SIZE);
		client.setFileType(FTPClient.BINARY_FILE_TYPE);
		// client.enterLocalPassiveMode();
		return client;
	}

	/**
	 * 注销客户端连接
	 * 
	 * @param client
	 *            FTP客户端对象
	 * @throws IOException
	 * @throws Exception
	 */
	protected void logout(FTPClient client) throws IOException {
		if (client != null) {
			try {
				// 退出登录,有些FTP服务器未实现此功能，若未实现则会出错
				client.logout();
			} catch (IOException e) {
				throw e;
			} finally {
				if (client.isConnected()) {
					// 断开连接
					client.disconnect();
				}
			}
		}
	}

	/**
	 * 获取FTP目录
	 * 
	 * @param url
	 *            原FTP目录
	 * @param dir
	 *            目录
	 * @return
	 * @throws MalformedURLException
	 */
	private URL getURL(URL url, String dir) throws MalformedURLException {
		String path = url.getPath();
		if (!path.endsWith("/") && !path.endsWith("//")) {
			path += "/";
		}
		dir = dir.replace("//", "/");
		if (dir.startsWith("/")) {
			dir = dir.substring(1);
		}
		path += dir;
		return new URL(url, path);
	}

	/**
	 * 获取FTP目录
	 * 
	 * @param dir
	 *            目录
	 * @return
	 * @throws MalformedURLException
	 * @throws Exception
	 */
	public URL getURL(String dir) throws MalformedURLException {
		return getURL(new URL(this.address), dir);
	}

	/**
	 * 上传文件或目录
	 * 
	 * @param dir
	 *            目标文件
	 * @param file
	 *            文件或目录对象数组
	 * @throws IOException
	 * @throws Exception
	 */
	public void upload(String dir, File... files) throws IOException {
		if (StringUtils.isEmpty(dir)) {
			return;
		}
		FTPClient client = null;
		try {
			client = getClient();

			changeAndMakeDirs(client, dir);

			for (File file : files) {
				client.storeFile(file.getName(), new FileInputStream(file));
			}
		} finally {
			logout(client);
		}
	}

	/**
	 * 上传文件或目录
	 * 
	 * @param dir
	 *            目标目录
	 * @param fileName
	 *            文件名称
	 * @param file
	 *            文件流
	 * @throws IOException
	 * @throws Exception
	 */
	public void upload(String dir, String fileName, InputStream file) throws IOException {
		if (StringUtils.isEmpty(dir)) {
			return;
		}
		FTPClient client = null;
		try {
			client = getClient();

			changeAndMakeDirs(client, dir);

			client.storeFile(fileName, file);
		} finally {
			logout(client);
		}
	}

	/**
	 * 移动并且创建目录
	 * 
	 * @param client
	 *            客户端
	 * @param path
	 *            路径(以'/'分隔)
	 * @throws IOException
	 */
	protected void changeAndMakeDirs(FTPClient client, String path) throws IOException {

		if (StringUtils.isBlank(path)) {
			return;
		}
		if (client.changeWorkingDirectory(path)) {
			return;
		}

		int idx = path.indexOf("/");

		if (idx >= 0) {
			String dir = path.substring(0, idx);
			this.changeAndMakeDir(client, dir);
			this.changeAndMakeDirs(client, path.substring(idx + 1));

		} else {
			this.changeAndMakeDir(client, path);
		}

	}

	/**
	 * 切换并创建目录
	 * 
	 * @param client
	 *            客户端
	 * @param dir
	 *            目录
	 * @throws IOException
	 */
	protected void changeAndMakeDir(FTPClient client, String dir) throws IOException {

		if (!client.changeWorkingDirectory(dir) && client.makeDirectory(dir)) {
			client.changeWorkingDirectory(dir);
		}
	}

	/**
	 * 下载文件或目录
	 * 
	 * @param localDir
	 *            本地存储目录
	 * @param dirs
	 *            文件或者目录
	 * @throws IOException
	 * @throws Exception
	 */
	public void download(String localDir, String... dirs) throws IOException {

		FTPClient client = null;
		try {
			client = getClient();
			File folder = new File(localDir);
			// 如果本地文件夹不存在，则创建
			if (!folder.exists()) {
				folder.mkdirs();
			}
			for (String dir : dirs) {
				FileUtils.copyFile(folder, client.storeFileStream(dir));
			}
		} finally {
			logout(client);
		}
	}

	/**
	 * 下载文件或目录
	 * 
	 * @param localDir
	 *            本地存储目录
	 * @param dirs
	 *            文件或者目录
	 * @throws IOException
	 */
	public OutputStream download(String dir) throws IOException {

		FTPClient client = null;
		try {
			client = getClient();
			return client.storeFileStream(dir);
		} finally {
			logout(client);
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
