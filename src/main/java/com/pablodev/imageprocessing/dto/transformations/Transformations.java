package com.pablodev.imageprocessing.dto.transformations;

import com.pablodev.imageprocessing.validation.annotations.NullableNotEmpty;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transformations {

    @Valid
    private Resize resize;

    @Valid
    private Crop crop;

    @Range(min = 1, max = 3)
    private Integer rotate;

    @NullableNotEmpty
    private String format;

    @NullableNotEmpty
    private String watermark;

    private Filters filters;
    private Boolean mirror;
    private Boolean flip;


}
