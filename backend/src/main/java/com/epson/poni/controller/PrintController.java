package com.epson.poni.controller;

import com.epson.poni.dto.Response;
import com.epson.poni.dto.print.RegistPrinter;
import com.epson.poni.service.print.PrintService;
import com.epson.poni.utils.PrinterManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/print")
@RequiredArgsConstructor
public class PrintController {
    private final PrintService printService;
    private final PrinterManager printerManager;

    @PostMapping("/regist")
    public Response registPrinter(@RequestBody RegistPrinter.Request request) {
        printService.regist(request);
        return new Response<>("201", "프린터 등록이 완료되었습니다.");
    }

    private static String UPLOAD_DIR = "C:/dev/";

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 저장할 파일 경로 생성
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.write(path, file.getBytes());

            // 파일 내용을 문자열로 읽기
            String content = new String(Files.readAllBytes(path));
            File convertedFile = new File(file.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(convertedFile);
            fos.write(file.getBytes());
            fos.close();
            printerManager.upload(convertedFile);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file";
        }
    }
}
