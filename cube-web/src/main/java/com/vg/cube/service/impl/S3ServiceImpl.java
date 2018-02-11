package com.vg.cube.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.vg.cube.service.FilesCrudService;

@Service
public class S3ServiceImpl extends FilesCrudService {

	private static final Logger LOGGER = LoggerFactory.getLogger(S3ServiceImpl.class);
	private static final String S3_PREFIX = "s3://";
	private static String UNDERSCORE = "_";

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private AmazonS3 amazonS3;

	public void uploadToStorage1(Collection<MultipartFile> files) throws IOException {
		String bucketName = createBucketIfNotExist("testbucketvg");
		files.forEach(multipartFile -> {
			LOGGER.info("Physical Resource Id - Bucket : {}", bucketName);
			String uniqueFileName = generateRandomFileId(multipartFile.getOriginalFilename());
			Resource resource = this.resourceLoader.getResource(S3_PREFIX + bucketName + "/" + uniqueFileName);

			if (WritableResource.class.isInstance(resource)) {
				WritableResource writableResource = (WritableResource) resource;

				try {
					try (OutputStream outputStream = writableResource.getOutputStream()) {
						outputStream.write(multipartFile.getBytes());
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

		});

	}

	public void uploadToStorage(Collection<MultipartFile> files) throws IOException {
		String bucketName = createBucketIfNotExist("testbucketvg");

		files.forEach(multipartFile -> {
			String uniqueFileName = generateRandomFileId(multipartFile.getOriginalFilename());

			uploadToS3(bucketName, multipartFile, uniqueFileName);

			S3Object s3O = this.amazonS3.getObject(bucketName, uniqueFileName);
			LOGGER.info("File uploaded to S3 : {}", s3O.getKey());
		});

	}

	private PutObjectRequest uploadToS3(String bucketName, MultipartFile multipartFile, String uploadKey) {
		PutObjectRequest putObjectRequest = null;
		try (InputStream inputStream = multipartFile.getInputStream()) {
			putObjectRequest = new PutObjectRequest(bucketName, uploadKey, inputStream, new ObjectMetadata());

			putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
			this.amazonS3.putObject(putObjectRequest);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return putObjectRequest;
	}

	private String createBucketIfNotExist(String bucketName) {

		boolean bucketExist = this.amazonS3.doesBucketExistV2(bucketName);
		if (!bucketExist) {
			// this.amazonS3Client.createBucket(bucketName);
		}
		return bucketName;
	}

	private String generateRandomFileId(String fileName) {
		return new StringBuilder().append(fileName).append(UNDERSCORE).append(UUID.randomUUID().toString())
				.append(UNDERSCORE).append(ZonedDateTime.now(ZoneId.of("UTC"))).toString();
	}
}
