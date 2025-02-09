package com.pablodev.imageprocessing.services.image.tranformers;

import com.pablodev.imageprocessing.dto.transformations.Crop;
import lombok.RequiredArgsConstructor;
import org.imgscalr.Scalr;
import org.springframework.lang.NonNull;

import java.awt.image.BufferedImage;

@RequiredArgsConstructor
public class CropTransformer implements Transformer {

    @NonNull
    private final Crop crop;

    @Override
    public BufferedImage transform(BufferedImage image) {
        return Scalr.crop(image, crop.getX(), crop.getY(), crop.getWidth(), crop.getHeight());
    }

}
