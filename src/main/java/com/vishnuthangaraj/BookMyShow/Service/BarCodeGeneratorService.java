package com.vishnuthangaraj.BookMyShow.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Service
@Getter
public class BarCodeGeneratorService {
    // QR-Code properties
    private static final String qrLocation = "./src/main/resources/static/QRCode.png"; // Default Location
    private static final int height = 250;
    private static final int width = 250;

    public void generateQR(String message) throws IOException, WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode
                (message, BarcodeFormat.QR_CODE, BarCodeGeneratorService.width, BarCodeGeneratorService.height);

        Path path = FileSystems.getDefault().getPath(BarCodeGeneratorService.qrLocation);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}
