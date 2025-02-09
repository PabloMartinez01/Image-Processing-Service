package com.pablodev.imageprocessing.services.image.tranformers;

import org.imgscalr.Scalr;

import java.awt.image.BufferedImage;

public class MirrorTransformer implements Transformer {

    @Override
    public BufferedImage transform(BufferedImage image) {
        return Scalr.rotate(image, Scalr.Rotation.FLIP_HORZ);
    }

}
