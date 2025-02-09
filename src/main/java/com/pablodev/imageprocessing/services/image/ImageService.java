package com.pablodev.imageprocessing.services.image;

import com.pablodev.imageprocessing.dto.image.ImageDataResponse;
import com.pablodev.imageprocessing.dto.image.ImageMetaResponse;
import com.pablodev.imageprocessing.dto.image.ImageRequest;
import com.pablodev.imageprocessing.dto.transformations.TransformationRequest;
import org.springframework.security.core.Authentication;

public interface ImageService {
    ImageMetaResponse saveImage(ImageRequest imageRequest, Authentication authentication);
    ImageMetaResponse updateImage(Integer id, ImageRequest imageRequest, Authentication authentication);
    ImageMetaResponse findImageById(Integer id, Authentication authentication);
    ImageDataResponse findImageDataById(Integer id, Authentication authentication);
    void deleteImage(Integer id, Authentication authentication);
    ImageDataResponse transformImage(Integer imageId, TransformationRequest transformationRequest, Authentication authentication);

}
