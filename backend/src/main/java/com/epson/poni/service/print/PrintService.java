package com.epson.poni.service.print;

import com.epson.poni.dto.cart.WorksheetPrintRequestDto;
import com.epson.poni.dto.print.EpsonTokenDto;
import com.epson.poni.dto.print.RegistPrinter;
import com.epson.poni.utils.PrinterManager;
import com.epson.poni.utils.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
@RequiredArgsConstructor
public class PrintService {

    private static String UPLOAD_DIR = "C:/dev/";
    private final PrinterManager printerManager;
    private final S3Uploader s3Uploader;

    public void regist(RegistPrinter.Request request) {
        String printerAddress = request.getPrinterAddress();
        // user 테이블에 추가하기 (deviceId)
        EpsonTokenDto epsonTokenDto = printerManager.getToken();
        System.out.println(epsonTokenDto.toString());
    }

    public String worksheetPrint(WorksheetPrintRequestDto worksheetPrintRequestDto) {

//        byte[] bytes = s3Uploader.DownloadFile(worksheetPrintRequestDto.getS3url());
//        MultipartFile file = new MockMultipartFile(worksheetPrintRequestDto.getS3url(),bytes);
//
//        // 저장할 파일 경로 생성
////            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
////            Files.write(path, file.getBytes());
////
////            // 파일 내용을 문자열로 읽기
////            String content = new String(Files.readAllBytes(path));
////            File convertedFile = new File(file.getOriginalFilename());
////            FileOutputStream fos = new FileOutputStream(convertedFile);
////            fos.write(file.getBytes());
////            fos.close();
////            파일 주소 받기
//        String localFilePath = "C:/dev/Section.jpeg";
        printerManager.print(worksheetPrintRequestDto.getS3url(), worksheetPrintRequestDto);
        return "File Print Success";
    }


}
