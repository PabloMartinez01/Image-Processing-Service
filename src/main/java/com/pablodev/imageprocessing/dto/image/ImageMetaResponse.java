package com.pablodev.imageprocessing.dto.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageMetaResponse {
    private Integer id;
    private String filename;
    private String format;
    private Long size;
}
