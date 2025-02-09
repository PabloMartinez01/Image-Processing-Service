package com.pablodev.imageprocessing.services.image.tranformers;

import lombok.RequiredArgsConstructor;
import org.imgscalr.Scalr;
import org.springframework.lang.NonNull;

import java.awt.image.BufferedImage;
import java.util.List;

import static org.imgscalr.Scalr.Rotation.*;

@RequiredArgsConstructor
public class RotateTransformer implements Transformer {

    private static final List<Scalr.Rotation> ROT = List.of(CW_90, CW_180, CW_270);

    @NonNull
    private final Integer rotate;

    @Override
    public BufferedImage transform(BufferedImage image) {
        return Scalr.rotate(image, ROT.get(rotate - 1));
    }
}
