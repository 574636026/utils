package com.injedu.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/**
 * 
 * 压缩文件夹工具
 *
 */
public class ZipUtils {

	/**
	 * 使用GBK编码可以避免压缩中文文件名乱码
	 */
	private static final String CHINESE_CHARSET = "GBK";

	/**
	 * 文件读取缓冲区大小
	 */
	private static final int CACHE_SIZE = 1024;

	/**
	 * 
	 * 压缩文件
	 * 
	 * @param inputFileName
	 *            输入的文件绝对路径
	 * @param zipFileName
	 *            输出压缩文件
	 * @throws IOException
	 */
	public static void zip(String inputFileName, String zipFileName) throws IOException {
		zip(new File(inputFileName), zipFileName);
	}

	/**
	 * 
	 * 压缩文件
	 * 
	 * @param inputFile
	 *            输入的文件
	 * @param zipFile
	 *            输出压缩文件
	 * @throws IOException
	 */
	public static void zip(File inputFile, File zipFile) throws IOException {

		ZipOutputStream out = null;

		try {

			out = new ZipOutputStream(new FileOutputStream(zipFile));

			zip(out, inputFile, "");

		} finally {

			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 
	 * 压缩文件
	 * 
	 * @param inputFile
	 *            输入的文件
	 * @param zipFileName
	 *            输出压缩文件
	 * @throws IOException
	 */
	public static void zip(File inputFile, String zipFileName) throws IOException {

		zip(inputFile, new File(zipFileName));

	}

	/**
	 * 
	 * @param out
	 * @param file
	 * @param base
	 * @throws IOException
	 */
	private static void zip(ZipOutputStream out, File file, String base) throws IOException {
		if (file.isDirectory()) {
			zipDirectory(file, out, base);
		} else {
			zipFile(file, out, base);
		}
	}

	/**
	 * 压缩一个目录
	 * 
	 * @throws IOException
	 * 
	 */
	private static void zipDirectory(File dir, ZipOutputStream out, String basedir) throws IOException {
		if (!dir.exists())
			return;

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			zip(out, files[i], basedir + dir.getName() + File.separator);
		}
	}

	/**
	 * 压缩一个文件
	 * 
	 * @param file
	 * @param out
	 * @param basedir
	 * @throws IOException
	 */
	private static void zipFile(File file, ZipOutputStream out, String basedir) throws IOException {
		if (!file.exists()) {
			return;
		}
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		ZipEntry entry = new ZipEntry(basedir + file.getName());
		out.putNextEntry(entry);
		int count;
		byte data[] = new byte[CACHE_SIZE];
		while ((count = bis.read(data, 0, CACHE_SIZE)) != -1) {
			out.write(data, 0, count);
		}
		bis.close();
	}

	/**
	 * <p>
	 * 解压压缩包
	 * </p>
	 * 
	 * @param zipFilePath
	 *            压缩文件路径
	 * @param destDir
	 *            压缩包释放目录
	 * @throws IOException
	 */
	public static void unZip(String zipFilePath, String destDir) throws IOException {

		ZipFile zipFile = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;

		try {

			zipFile = new ZipFile(zipFilePath, CHINESE_CHARSET);
			Enumeration<?> emu = zipFile.getEntries();
			File file, parentFile;
			ZipEntry entry;
			byte[] cache = new byte[CACHE_SIZE];
			while (emu.hasMoreElements()) {
				entry = (ZipEntry) emu.nextElement();
				if (entry.isDirectory()) {
					new File(destDir + entry.getName()).mkdirs();
					continue;
				}
				bis = new BufferedInputStream(zipFile.getInputStream(entry));
				file = new File(destDir + entry.getName());
				parentFile = file.getParentFile();
				if (parentFile != null && (!parentFile.exists())) {
					parentFile.mkdirs();
				}
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos, CACHE_SIZE);
				int nRead = 0;
				while ((nRead = bis.read(cache, 0, CACHE_SIZE)) != -1) {
					fos.write(cache, 0, nRead);
				}

			}

		} finally {
			if (bos != null) {
				bos.flush();
				bos.close();
			}
			if (bis != null) {
				bis.close();
			}
			if (fos != null) {
				fos.close();
			}
			if (zipFile != null) {
				zipFile.close();
			}
		}

	}

}