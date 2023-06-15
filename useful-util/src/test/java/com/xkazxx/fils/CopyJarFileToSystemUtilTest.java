package com.xkazxx.fils;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class CopyJarFileToSystemUtilTest {


	@Test
	public void copyFileFromJar(){

		String path = "copy/testcopy.txt";
		String copytarget = "E:\\learning-project\\learning_demo\\useful-util\\src\\main\\resources\\copytarget\\testcopy.txt";
		File file = new File(copytarget);
		try {
			Assert.assertEquals(false, file.exists());
			CopyJarFileToSystemUtil.copyFileFromJar(path, copytarget);
			Assert.assertEquals(true, file.exists());
		} finally {
			FileUtils.delFiles(file);
		}

	}

	@Test
	public void dirCopyFileFromJar() {
		String dirPath = "copy";
		String copytarget = "E:\\learning-project\\learning_demo\\useful-util\\src\\main\\resources\\copytarget";
		File file = new File(copytarget);
		try {
			Assert.assertEquals(0, file.listFiles().length);
			CopyJarFileToSystemUtil.dirCopyFileFromJar(dirPath, copytarget);
			Assert.assertEquals(3, file.listFiles().length);
		} finally {
			FileUtils.delFiles(file);
		}
	}


}