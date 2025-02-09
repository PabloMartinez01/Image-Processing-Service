package com.pablodev.imageprocessing.services.image;

import com.pablodev.imageprocessing.dto.image.ImageDataResponse;
import com.pablodev.imageprocessing.dto.image.ImageMetaResponse;
import com.pablodev.imageprocessing.dto.image.ImageRequest;
import com.pablodev.imageprocessing.dto.transformations.TransformationRequest;
import com.pablodev.imageprocessing.dto.transformations.Transformations;
import com.pablodev.imageprocessing.mappers.ImageMapper;
import com.pablodev.imageprocessing.model.Image;
import com.pablodev.imageprocessing.model.User;
import com.pablodev.imageprocessing.repositories.ImageRepository;
import com.pablodev.imageprocessing.services.awt.AwsService;
import com.pablodev.imageprocessing.services.image.tranformers.Transformer;
import com.pablodev.imageprocessing.services.image.tranformers.TransformerFactory;
import com.pablodev.imageprocessing.utils.ImageUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.BufferedImage;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultImageService implements ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    private final AwsService awsService;
    private final ImageUtils imageUtils;
    private final TransformerFactory transformerFactory;

    @Override
    public ImageMetaResponse saveImage(ImageRequest imageRequest, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Image image = imageMapper.toImageEntity(imageRequest);
        image.setUser(user);

        Image savedImage = imageRepository.save(image);
        awsService.saveImage(savedImage, imageRequest.getData());

        return imageMapper.toImageMetaResponse(savedImage);
    }

    @Override
    public ImageMetaResponse updateImage(Integer id, ImageRequest imageRequest, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Image existentImage = imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image with id " + id + " not found"));

        if (!existentImage.getUser().getId().equals(user.getId())) {
            throw new SecurityException("You do not have permission to access this resource");
        }

        Image newImage = imageMapper.toImageEntity(imageRequest);
        newImage.setId(id);
        newImage.setUser(user);

        Image updatedImage = imageRepository.save(newImage);
        awsService.saveImage(updatedImage, imageRequest.getData());

        return imageMapper.toImageMetaResponse(updatedImage);
    }

    @Override
    @Transactional(readOnly = true)
    public ImageMetaResponse findImageById(Integer id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image with id " + id + " not found"));

        if (!image.getUser().getId().equals(user.getId())) {
            throw new SecurityException("You do not have permission to access this resource");
        }

        return imageMapper.toImageMetaResponse(image);
    }

    @Override
    @Transactional(readOnly = true)
    public ImageDataResponse findImageDataById(Integer id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image with id " + id + " not found"));

        if (!image.getUser().getId().equals(user.getId())) {
            throw new SecurityException("You do not have permission to access this resource");
        }

        byte[] imageBytes = awsService.getImageBytes(image.getFilename());

        return ImageDataResponse.builder()
                .data(imageBytes)
                .format(image.getFormat())
                .build();
    }

    @Override
    public void deleteImage(Integer id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image with id " + id + " not found"));

        if (!image.getUser().getId().equals(user.getId())) {
            throw new SecurityException("You do not have permission to access this resource");
        }

        imageRepository.delete(image);
        awsService.deleteImage(image.getFilename());
    }

    @Override
    public ImageDataResponse transformImage(Integer imageId, TransformationRequest transformationRequest, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Image with ID %d was not found.", imageId)));

        if (!image.getUser().getId().equals(user.getId())) {
            throw new SecurityException("You do not have permission to access this resource");
        }

        byte[] imageBytes = awsService.getImageBytes(image.getFilename());
        BufferedImage bufferedImage = imageUtils.convertBytesToImage(imageBytes);

        Transformations transformations = transformationRequest.getTransformations();
        for (Transformer transformer : transformerFactory.buildTransformerList(transformations)) {
            bufferedImage = transformer.transform(bufferedImage);
        }

        String format = imageUtils.validateFormat(transformations.getFormat())
                .orElse(image.getFormat());

        byte[] responseBytes = imageUtils.convertImageToBytes(bufferedImage, format);

        return ImageDataResponse.builder()
                .data(responseBytes)
                .format(format)
                .build();
    }

}
