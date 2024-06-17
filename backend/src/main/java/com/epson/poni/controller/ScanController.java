package com.epson.poni.controller;

import com.epson.poni.dto.Response;
import com.epson.poni.service.print.ScanService;
import com.epson.poni.utils.S3Uploader;
import jakarta.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/scan")
@RequiredArgsConstructor
public class ScanController {
    private final ScanService scanService;

    @PostMapping("/load")
    public void scanData(HttpServletRequest request) throws IOException{
        scanService.saveImage(request);
    }

    @PostMapping("/regist")
    public Response registDestination() {
        scanService.registDestination();
        return new Response<>("201", "스캔 대상 등록 완료");
    }
}
