package com.epson.poni.utils;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${s3.url}")
    private String url;

    public String UploadFileUsingStream(String fileName, ByteArrayOutputStream outputStream) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(outputStream.size());

        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, metadata));
        System.out.println("PDF 파일이 S3에 성공적으로 업로드되었습니다: " + fileName);

        return url + fileName;
    }
}
