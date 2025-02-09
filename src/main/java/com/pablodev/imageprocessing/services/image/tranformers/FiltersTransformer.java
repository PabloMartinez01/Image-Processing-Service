package com.pablodev.imageprocessing.services.image.tranformers;

import com.pablodev.imageprocessing.dto.transformations.Filters;
import lombok.RequiredArgsConstructor;
import org.imgscalr.Scalr;
import org.springframework.lang.NonNull;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Function;

@RequiredArgsConstructor
public class FiltersTransformer implements Transformer {

    @NonNull
    private final Filters filters;

    @Override
    public BufferedImage transform(BufferedImage image) {
        image = applyFilter(image, filters.isGrayscale(), FiltersTransformer::applyGrayscale);
        image = applyFilter(image, filters.isSepia(), FiltersTransformer::applySepia);
        return image;
    }

    private static BufferedImage applyFilter(BufferedImage image, boolean condition, Function<BufferedImage, BufferedImage> function) {
        return condition ? function.apply(image) : image;
    }

    private static BufferedImage applyGrayscale(BufferedImage image) {
        return Scalr.apply(image, Scalr.OP_GRAYSCALE);
    }

    private static BufferedImage applySepia(BufferedImage image) {
        BufferedImage sepiaImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color originalColor = new Color(image.getRGB(x, y));

                int red = originalColor.getRed();
                int green = originalColor.getGreen();
                int blue = originalColor.getBlue();

                int newRed = (int) Math.min(0.393 * red + 0.769 * green + 0.189 * blue, 255);
                int newGreen = (int) Math.min(0.349 * red + 0.686 * green + 0.168 * blue, 255);
                int newBlue = (int) Math.min(0.272 * red + 0.534 * green + 0.131 * blue, 255);

                Color sepiaColor = new Color(newRed, newGreen, newBlue);
                sepiaImage.setRGB(x, y, sepiaColor.getRGB());
            }
        }

        return sepiaImage;
    }
}
