package com.xkazxx.fils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.FileCopyUtils;

import java.io.*;

/**
 * 复制jar包中的文件或者文件夹到当前文件系统中
 * 程序运行时是jar包的方式
 *
 * @version v0.1
 * @author: created by xkazxx
 * @description: description
 * @date: 2023/4/26 23:12
 **/
public class CopyJarFileToSystemUtil {
	private final static Logger log = LoggerFactory.getLogger(CopyJarFileToSystemUtil.class);

	/**
	 * 复制单个文件 从classpath中读取文件复制
	 *
	 * @param path    不能以/开头   指向文件不能是目录
	 * @param newPath 指向文件不能是目录
	 */
	public static void copyFileFromJar(String path, String newPath) {
		try {
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			Resource resource = resolver.getResource(path);
			//创建新文件
			makeFile(newPath);
			FileCopyUtils.copy(resource.getInputStream(), new FileOutputStream(newPath));
		} catch (IOException e) {
			log.error("copyFileFromJar:", e);
		}
	}

	/**
	 * 复制path目录下所有文件
	 *
	 * @param path    文件目录 不能以/开头
	 * @param newPath 新文件目录
	 */
	public static void dirCopyFileFromJar(String path, String newPath) {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		String tmp = "!/" + path;
		try {
			//获取所有匹配的文件
			Resource[] resources = resolver.getResources(path + "/**");
			//打印有多少文件
			log.info("*****************" + resources.length);
			for (Resource resource : resources) {
				String resourcePath = resource.getURL().getPath();
				if (resourcePath.endsWith("/")) {
					continue;
				}
				String name = resourcePath.substring(resourcePath.lastIndexOf(tmp) + tmp.length());
				String targetPath = newPath + name;
				makeFile(targetPath);

				FileCopyUtils.copy(resource.getInputStream(), new FileOutputStream(targetPath));

			}

		} catch (Exception e) {
			log.error("dirCopyFileFromJar:", e);
			e.printStackTrace();
		}
	}

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


