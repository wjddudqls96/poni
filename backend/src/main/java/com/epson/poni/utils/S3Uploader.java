package com.epson.poni.utils;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
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

    public byte[] DownloadFile(String url){
        String filename = url.substring(url.lastIndexOf('/') + 1);

        S3Object s3Object = amazonS3Client.getObject(bucket, filename);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            // e.printStackTrace();
            throw new IllegalStateException("aws s3 다운로드 error");
        }
    }
}
