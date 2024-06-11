package com.epson.poni.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(500, "서버에 문제가 생겼습니다."),
    ILLEGAL_ARGUMENT_EXCEPTION(400,"올바르지 않은 형식의 입력입니다."),
    PDF_GENERATION_FAILED(500, "PDF 생성 과정 중 예기치 못한 문제가 발생했습니다."),
    PDF_UPLOAD_FAILED(503, "S3 서비스에 접근할 수 없거나 서비스가 일시적으로 오버로드되어 요청을 처리할 수 없습니다."),
    NOT_FOUNT_HTML_RESOURCE(404, "HTML 리소스의 위치를 찾을 수 없습니다."),
    S3_CLIENT_ERROR(503, "자격 증명이 잘못되었거나, 네트워크 문제 등 클라이언트 측 오류입니다."),
    S3_SERVICE_ERROR(503, "AWS S3 서비스 자체에서 발생하는 문제가 발생했습니다.")
    ;


    private int status;
    private String message;


    private static final Map<String, ErrorCode> messageMap = Collections.unmodifiableMap(
            Stream.of(values()).collect(
                    Collectors.toMap(ErrorCode::getMessage, Function.identity())));

    public static ErrorCode fromMessage(String message){
        return messageMap.get(message);
    }
}
