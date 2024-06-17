package com.epson.poni.service.print;

import com.epson.poni.dto.print.EpsonTokenDto;
import com.epson.poni.dto.print.RegistPrinter;
import com.epson.poni.utils.PrinterManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrintService {

    private final PrinterManager printerManager;
    public void regist(RegistPrinter.Request request) {
        String printerAddress = request.getPrinterAddress();
        // user 테이블에 추가하기 (deviceId)
        EpsonTokenDto epsonTokenDto = printerManager.getToken();
        System.out.println(epsonTokenDto.toString());
    }
}
