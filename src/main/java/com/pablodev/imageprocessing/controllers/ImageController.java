package com.pablodev.imageprocessing.controllers;

import com.pablodev.imageprocessing.dto.image.ImageDataResponse;
import com.pablodev.imageprocessing.dto.image.ImageMetaResponse;
import com.pablodev.imageprocessing.dto.image.ImageRequest;
import com.pablodev.imageprocessing.dto.transformations.TransformationRequest;
import com.pablodev.imageprocessing.services.image.ImageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
@Tag(name = "Images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageMetaResponse> saveImage(@RequestParam MultipartFile image, Authentication authentication) throws IOException {
        ImageRequest imageRequest = ImageRequest.builder()
                .filename(image.getOriginalFilename())
                .format(extractExtension(image.getOriginalFilename()))
                .size(image.getSize())
                .data(image.getBytes())
                .build();

        ImageMetaResponse response = imageService.saveImage(imageRequest, authentication);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageMetaResponse> updateImage(@PathVariable Integer id, @RequestParam MultipartFile image, Authentication authentication) throws IOException {
        ImageRequest imageRequest = ImageRequest.builder()
                .filename(image.getOriginalFilename())
                .format(extractExtension(image.getOriginalFilename()))
                .size(image.getSize())
                .data(image.getBytes())
                .build();

        ImageMetaResponse response = imageService.updateImage(id, imageRequest, authentication);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageDataResponse> deleteImage(@PathVariable Integer id, Authentication authentication) {
        imageService.deleteImage(id, authentication);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageMetaResponse> findImageMeta(@PathVariable Integer id, Authentication authentication) {
        ImageMetaResponse imageMetaResponse = imageService.findImageById(id, authentication);
        return ResponseEntity.ok(imageMetaResponse);
    }

    @GetMapping(value = "/{id}/view", produces = "image/*")
    public ResponseEntity<byte[]> findImageData(@PathVariable Integer id, Authentication authentication) {
        ImageDataResponse imageDataResponse = imageService.findImageDataById(id, authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf("image/" + imageDataResponse.getFormat()));

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(imageDataResponse.getData());
    }

    @PostMapping(value = "/{id}/transform", produces = "image/*")
    public ResponseEntity<byte[]> transformImage(@PathVariable Integer id, @Valid @RequestBody TransformationRequest transformationRequest,
            Authentication authentication) {
        ImageDataResponse imageDataResponse = imageService.transformImage(id, transformationRequest, authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf("image/" + imageDataResponse.getFormat()));

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(imageDataResponse.getData());
    }

    private String extractExtension(String filename) {
        return  Objects.requireNonNull(filename).substring(filename.lastIndexOf(".") + 1);
    }

}
