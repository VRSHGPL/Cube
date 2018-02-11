package com.vg.cube.service;

import java.io.IOException;
import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;

public abstract class FilesCrudService {

	public abstract void uploadToStorage(Collection<MultipartFile> files) throws IOException;

}
