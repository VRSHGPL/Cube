package com.vg.cube.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.vg.cube.service.FilesCrudService;

@RestController
@RequestMapping("/api/file")
public class FileOperationsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileOperationsController.class);

	@Autowired
	private FilesCrudService filesCrudService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public boolean uploadFile(MultipartHttpServletRequest request) {

		request.getFileMap().forEach((k, v) -> {
			LOGGER.info("File Name : {}", v.getOriginalFilename());
		});

		try {
			filesCrudService.uploadToStorage(request.getFileMap().values());
		} catch (Exception e) {
			LOGGER.error("Upload failed : {}", e);
			return false;
		}
		return true;

	}
}
