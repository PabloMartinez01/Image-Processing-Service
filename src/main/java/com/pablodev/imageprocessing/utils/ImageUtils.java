package com.pablodev.imageprocessing.utils;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class ImageUtils {

    private static final List<String> FORMATS = List.of("png", "jpg", "jpeg", "gif", "bmp", "webp", "tiff");

    public Optional<String> validateFormat(String newFormat) {
        return Optional.ofNullable(newFormat)
                .map(String::toLowerCase)
                .filter(FORMATS::contains);
    }

    public byte[] convertImageToBytes(BufferedImage image, String format)  {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, format, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage convertBytesToImage(byte[] bytes) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        try {
            return ImageIO.read(byteArrayInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
