package com.pablodev.imageprocessing.services.image.tranformers;

import com.pablodev.imageprocessing.dto.transformations.Resize;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.imgscalr.Scalr;

import java.awt.image.BufferedImage;

@AllArgsConstructor
public class ResizeTransformer implements Transformer {

    @NonNull
    private final Resize resize;

    @Override
    public BufferedImage transform(BufferedImage image) {
        return Scalr.resize(image, resize.getWidth(), resize.getHeight());
    }
}
