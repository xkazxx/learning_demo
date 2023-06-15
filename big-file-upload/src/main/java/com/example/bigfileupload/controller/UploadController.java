package com.example.bigfileupload.controller;

import com.xkazxx.fils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.util.UUID;

/**
 * @version v0.1
 * @author: created by xkazxx
 * @description: description
 * @date: 2023/6/15 22:53
 **/
@RestController
@RequestMapping("/file/upload")
public class UploadController {
	private final static Logger log = LoggerFactory.getLogger(UploadController.class);

	@Value("${xkazxx.file.upload.tmp.dir}")
	private String tmpDir;

	/**
	 *
	 * @param multipartFile 参数名称必须和上传的文件对应的表单参数名称一致
	 * @return
	 */
	@GetMapping("/single")
	public String singleUpload( MultipartFile multipartFile) {
		String originalFilename = multipartFile.getOriginalFilename();
		if (StringUtils.hasText(originalFilename)) {
			originalFilename = Normalizer.normalize(originalFilename, Normalizer.Form.NFKD);
		} else {
			originalFilename = UUID.fromString(String.valueOf(System.currentTimeMillis())).toString();
		}
		String filePath = tmpDir + File.separatorChar + originalFilename;
		try {
			FileUtils.makeFile(filePath);
			FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(filePath));
		} catch (IOException e) {
			log.error("singleUpload IOException : ", e);
			return "Failed";
		}
		return "Success";
	}

	@PostMapping("/chunk/init")
	public String chunkInit() {

	}

	@PostMapping("/chunk/start")
	public String chunkUpload() {

	}

	@PostMapping("/chunk/merge")
	public String chunkMerge() {

	}



}
