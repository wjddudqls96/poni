package com.epson.poni.config;


import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.epson.poni.error.ErrorCode;
import com.epson.poni.error.exception.BaseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public AmazonS3Client amazonS3Client() {
        try {
            //AWS 서비스에 접근하기 위한 기본적인 자격증명 정보인 access key와 secret key를 받아 객체를 생성
            BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

            //표준 구성으로 초기화, aws 클라이언트가 작동할 리전 설정, AWS 서비스에 접속할 때 사용할 자격 증명 공급자를 설정
            return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                    .withRegion(region)
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                    .build();
        } catch (AmazonServiceException e) {
            // 서비스 측 오류 처리
            throw new BaseException(ErrorCode.S3_SERVICE_ERROR);
        } catch (AmazonClientException e) {
            // 클라이언트 측 연결 오류 처리
            throw new BaseException(ErrorCode.S3_CLIENT_ERROR);
        }

    }

}
