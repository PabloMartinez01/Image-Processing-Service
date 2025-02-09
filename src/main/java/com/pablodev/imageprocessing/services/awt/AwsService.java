package com.pablodev.imageprocessing.services.awt;

import com.pablodev.imageprocessing.model.Image;

public interface AwsService {
    void saveImage(Image image, byte[] imageData);
    byte[] getImageBytes(String filename);
    void deleteImage(String filename);
}
