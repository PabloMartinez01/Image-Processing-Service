package com.pablodev.imageprocessing.dto.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageRequest {
    private String filename;
    private String format;
    private Long size;
    private byte[] data;
}
