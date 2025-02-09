package com.pablodev.imageprocessing.services.image.tranformers;

import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.awt.image.BufferedImage;

@RequiredArgsConstructor
public class WatermarkTransformer implements Transformer {

    private final String watermark;

    @Override
    public BufferedImage transform(BufferedImage image) {

        if (watermark == null)
            return image;

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage watermarkedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = (Graphics2D) watermarkedImage.getGraphics();
        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);

        g2d.drawImage(image, 0, 0, null);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(alphaChannel);
        g2d.setFont(new Font("Arial", Font.BOLD, 48));

        FontMetrics fontMetrics = g2d.getFontMetrics();

        int textWidth = fontMetrics.stringWidth(watermark);
        int textHeight = fontMetrics.getHeight();

        int x = (width - textWidth) / 2;
        int y = (height + textHeight) / 2;

        g2d.drawString(watermark, x, y);
        g2d.dispose();

        return watermarkedImage;
    }
}
