package com.org.utils.file;

import org.apache.commons.io.FileUtils;

import java.io.IOException;

public class FileUtilsTest {

	public static void main(String[] args) throws IOException {
		
		System.out.println(FileUtils.byteCountToDisplaySize(111111111));
		
	}
	
}
