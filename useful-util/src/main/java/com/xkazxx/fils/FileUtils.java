package com.xkazxx.fils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @version v0.1
 * @author: created by xkazxx
 * @description: description
 * @date: 2023/6/15 23:09
 **/

public class FileUtils {
	private final static Logger log = LoggerFactory.getLogger(FileUtils.class);
	/**
	 * 创建文件
	 *
	 * @param path 全路径 指向文件
	 */
	public static void makeFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			return;
		}
		if (path.endsWith(File.separator)) {
			makeDir(path);
			return;
		}
		if (!file.getParentFile().exists()) {
			makeDir(file.getParent());
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			log.info("创建文件{}失败！{}", path, e.getMessage());
		}
	}

	public static void makeDir(String path) {
		File file = new File(path);
		if (file.exists()) {
			return;
		}
		if (file.isFile()) {
			makeDir(path);
			return;
		}
		if (!file.getParentFile().exists()) {
			makeFile(file.getParent());
		}
		file.mkdir();
	}

	public static void delFiles(File target) {
		if (target == null || !target.exists()) {
			return;
		}
		if (target.isFile()) {
			target.delete();
			return;
		}
		File[] files = target.listFiles();
		for (File file : files) {
			delFiles(file);
		}
	}
}
