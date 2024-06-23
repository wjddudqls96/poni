package com.epson.poni.service.print;

import com.epson.poni.dto.dictation.DifficultyGradingResponseDto;
import com.epson.poni.service.Dictation.DictationService;
import com.epson.poni.utils.PrinterManager;
import com.epson.poni.utils.S3Uploader;
import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
@RequiredArgsConstructor
public class ScanService {
    private final PrinterManager printerManager;
    private final DictationService dictationService;

    private final S3Uploader s3Uploader;

    public void registDestination() {
        printerManager.registDestination();
    }

    public DifficultyGradingResponseDto saveImage(HttpServletRequest request) throws IOException {
        DifficultyGradingResponseDto difficultyGradingResponseDto = null;
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iterator = multipartRequest.getFileNames();

            while (iterator.hasNext()) {
                String key = iterator.next();
                MultipartFile file = multipartRequest.getFile(key);

                if ((file!=null)){
                    difficultyGradingResponseDto = dictationService.difficultyGrading(file);
                }

//                if (file != null) {
//                    ByteArrayOutputStream outputStream = convertMultipartFileToByteArrayOutputStream(file);
//                    String url = s3Uploader.UploadFileUsingStream(file.getOriginalFilename(),outputStream);
//                    // S3 저장 url
//                    System.out.println(url);
//                }
            }

            // 폼 데이터 (일반 텍스트 데이터)도 처리할 수 있습니다.
//            multipartRequest.getParameterMap().forEach((name, values) -> {
//                System.out.println("Parameter name: " + name);
//                for (String value : values) {
//                    System.out.println("3");
//                    System.out.println("Parameter value: " + value);
//                }
//            });
        } else {
            // 멀티파트 요청이 아닌 경우 처리
            String data = new BufferedReader(request.getReader())
                .lines().collect(Collectors.joining("\n"));
            System.out.println(data);
        }

        return difficultyGradingResponseDto;
    }

    // MultipartFile을 ByteArrayOutputStream으로 변환하는 메서드
    private static ByteArrayOutputStream convertMultipartFileToByteArrayOutputStream(MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outputStream.close();

        return outputStream;
    }
}
