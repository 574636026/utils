package com.injedu.utils.file;

import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtilsTest {

	public static void main(String[] args) throws IOException {
		
		System.out.println(FileUtils.byteCountToDisplaySize(111111111));
		
	}
	
}
