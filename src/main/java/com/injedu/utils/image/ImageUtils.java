package com.injedu.utils.image;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

/**
 * 
 * 图片处理器
 *
 * @author joy.zhou
 * @date 2016年1月20日
 * @version 1.0
 *
 */
public class ImageUtils {

	/** 默认质量压缩类型 */
	private static final String DEFAULT_COMPRESS_FORMAT = "jpg";
	/** 图片源 */
	private InputStream src;
	/** 图片类型 */
	private String type;
	/** 是否压缩 */
	private boolean compress;
	/** 处理的图片 */
	private BufferedImage image;

	/**
	 * 
	 * 构造图片处理器
	 * 
	 * @param src
	 *            图片源
	 * @param type
	 *            图片类型
	 * @throws IOException
	 */
	public ImageUtils(InputStream src, String type) throws IOException {
		this.src = src;
		this.type = type;
		this.image = ImageIO.read(this.src);
	}

	/**
	 * 
	 * 构造图片处理器
	 * 
	 * @param src
	 *            图片源
	 * @param type
	 *            图片类型
	 * @param compress
	 *            是否质量压缩
	 * @throws IOException
	 */
	public ImageUtils(InputStream src, String type, boolean compress) throws IOException {
		this(src, type);
		this.compress = compress;
	}

	/**
	 * 
	 * 构造图片处理器
	 * 
	 * @param file
	 *            图片文件
	 * @throws IOException
	 */
	public ImageUtils(File file) throws IOException {
		this.src = new FileInputStream(file);
		this.type = getFileType(file);
		this.image = ImageIO.read(this.src);
	}

	/**
	 * 
	 * 构造图片处理器
	 * 
	 * @param file
	 *            图片文件
	 * @param compress
	 *            是否压缩
	 * @throws IOException
	 */
	public ImageUtils(File file, boolean compress) throws IOException {
		this(file);
		this.compress = compress;
	}

	/**
	 * 图片裁切
	 * 
	 * @param src
	 *            图片源
	 * @param filetype
	 *            图片类型
	 * @param x
	 *            裁切起始点x坐标
	 * @param y
	 *            裁切起始点y坐标
	 * @param width
	 *            裁切宽度
	 * @param height
	 *            裁切高度
	 * @return
	 * @throws IOException
	 */
	public void cut(int x, int y, int width, int height) throws IOException {

		this.image = this.image.getSubimage(x, y, width, height);
	}

	/**
	 * 输出图片流(最后会关闭源文件)
	 * 
	 * @param isCompress
	 *            是否压缩
	 * @return
	 * @throws IOException
	 */
	public InputStream transfer() throws IOException {

		InputStream is = null;

		ByteArrayOutputStream bs = null;

		ImageOutputStream imOut = null;

		try {

			bs = new ByteArrayOutputStream();

			if (this.compress) {
				compress(this.image, bs);
			} else {
				imOut = ImageIO.createImageOutputStream(bs);
				ImageIO.write(this.image, this.type, imOut);
			}

			is = new ByteArrayInputStream(bs.toByteArray());

		} finally {

			if (bs != null) {
				bs.close();
			}
			if (imOut != null) {
				imOut.close();
			}
			if (this.src != null)
				this.src.close();
		}
		return is;
	}

	/**
	 * 输出图片(最后会关闭源文件)
	 * 
	 * @param image
	 *            图片
	 * @param filetype
	 *            图片类型
	 * @param target
	 *            输出目标
	 * @throws IOException
	 */
	public void transferTo(File target) throws IOException {

		try {

			if (this.compress) {

				compress(this.image, new FileOutputStream(target));

			} else {

				ImageIO.write(this.image, this.type, target);
			}

		} finally {

			if (this.src != null)
				this.src.close();
		}

	}

	/**
	 * 
	 * 图片质量压缩
	 * 
	 * @throws IOException
	 * 
	 */
	protected void compress(BufferedImage bi, OutputStream out) throws IOException {

		ImageOutputStream imOut = null;
		try {
			imOut = ImageIO.createImageOutputStream(out);
			// 指定写图片的方式为 jpg
			ImageWriter imgWrier = ImageIO.getImageWritersByFormatName(DEFAULT_COMPRESS_FORMAT).next();
			ImageWriteParam imgWriteParams = new JPEGImageWriteParam(null);
			// 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
			imgWriteParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			// 这里指定压缩的程度，参数qality是取值0~1范围内，
			imgWriteParams.setCompressionQuality((float) 0.2);
			imgWriteParams.setProgressiveMode(ImageWriteParam.MODE_DEFAULT);
			ColorModel colorModel = ColorModel.getRGBdefault();
			// 指定压缩时使用的色彩模式
			imgWriteParams.setDestinationType(
					new ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));
			imgWrier.reset();
			imgWrier.setOutput(imOut);
			// 调用write方法，就可以向输入流写图片
			imgWrier.write(null, new IIOImage(bi, null, null), imgWriteParams);
		} finally {
			imOut.close();
		}

	}

	/**
	 * 获取文件后缀
	 * 
	 * @param name
	 * @return
	 */
	private String getFileType(File file) {

		String name = file.getName();
		String suiff = "";
		int idx = name.lastIndexOf(".");
		if (idx > -1) {
			suiff = name.substring(idx + 1);
		}
		return suiff;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCompress(boolean compress) {
		this.compress = compress;
	}

}
