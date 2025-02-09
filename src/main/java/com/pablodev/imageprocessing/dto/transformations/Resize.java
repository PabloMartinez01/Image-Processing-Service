package com.pablodev.imageprocessing.dto.transformations;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resize {

    @NotNull
    @Range(min = 100, max = 2000)
    private Integer width;

    @NotNull
    @Range(min = 100, max = 2000)
    private Integer height;

}
