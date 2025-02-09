package com.pablodev.imageprocessing.services.awt;

import com.pablodev.imageprocessing.model.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultAwsService implements AwsService {

    @Value("${aws.s3.bucket}")
    private String bucketName;

    private final S3Client s3Client;
    private final CacheManager cacheManager;

    @Override
    public void saveImage(Image image, byte[] imageData) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(image.getFilename())
                .contentLength(image.getSize())
                .contentType("image/" + image.getFormat())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(imageData));

        Optional.ofNullable(cacheManager.getCache("images"))
                .ifPresent(c -> c.put(image.getFilename(), imageData));
    }

    @Override
    @Cacheable(value = "images", key = "#filename")
    public byte[] getImageBytes(String filename) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();

        return s3Client.getObject(getObjectRequest, ResponseTransformer.toBytes()).asByteArray();
    }

    @Override
    @CacheEvict(value = "images", key = "#filename")
    public void deleteImage(String filename) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();

        s3Client.deleteObject(deleteObjectRequest);
    }

}
