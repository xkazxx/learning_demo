package com.xkazxx.fils.test;

import com.xkazxx.fils.CopyJarFileToSystemUtil;
import com.xkazxx.fils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class CopyJarFileToSystemUtilMain {
	private final static Logger log = LoggerFactory.getLogger(CopyJarFileToSystemUtilMain.class);

	public static void main(String[] args) {
		copyFileFromJar();
		dirCopyFileFromJar();
	}

	public static void copyFileFromJar() {

		String path = "copy/testcopy.txt";
		String copytarget = "E:\\learning-project\\learning_demo\\useful-util\\src\\main\\resources\\copytarget\\testcopy.txt";
		File file = new File(copytarget);
		try {
			log.info("copyFileFromJar start {}", file.exists());
			CopyJarFileToSystemUtil.copyFileFromJar(path, copytarget);
			log.info("copyFileFromJar start {}", file.exists());
		} finally {
			FileUtils.delFiles(file);
		}

	}

	public static void dirCopyFileFromJar() {
		String dirPath = "copy";
		String copytarget = "E:\\learning-project\\learning_demo\\useful-util\\src\\main\\resources\\copytarget";
		File file = new File(copytarget);
		try {
			log.info("dirCopyFileFromJar start {}", file.listFiles().length);
			CopyJarFileToSystemUtil.dirCopyFileFromJar(dirPath, copytarget);
			log.info("dirCopyFileFromJar start {}", file.listFiles().length);
		} finally {
			FileUtils.delFiles(file);
		}
	}


}