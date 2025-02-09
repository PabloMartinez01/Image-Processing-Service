package com.pablodev.imageprocessing.services.image.tranformers;

import java.awt.image.BufferedImage;

public interface Transformer {
    BufferedImage transform(BufferedImage image);
}
