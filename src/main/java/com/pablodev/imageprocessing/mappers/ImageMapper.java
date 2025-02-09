package com.pablodev.imageprocessing.mappers;

import com.pablodev.imageprocessing.dto.image.ImageMetaResponse;
import com.pablodev.imageprocessing.dto.image.ImageRequest;
import com.pablodev.imageprocessing.model.Image;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ImageMapper {

    public Image toImageEntity(ImageRequest imageRequest) {
        return Image.builder()
                .filename(UUID.randomUUID() + "_" + imageRequest.getFilename())
                .format(imageRequest.getFormat())
                .size(imageRequest.getSize())
                .build();
    }

    public ImageMetaResponse toImageMetaResponse(Image image) {
        return ImageMetaResponse.builder()
                .id(image.getId())
                .filename(image.getFilename())
                .size(image.getSize())
                .format(image.getFormat())
                .build();
    }

}
