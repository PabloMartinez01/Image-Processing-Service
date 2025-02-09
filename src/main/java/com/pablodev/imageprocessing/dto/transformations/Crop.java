package com.pablodev.imageprocessing.dto.transformations;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Crop {

    @NotNull
    private Integer width;

    @NotNull
    private Integer height;

    @NotNull
    private Integer x;

    @NotNull
    private Integer y;

}
