package com.pablodev.imageprocessing.dto.transformations;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransformationRequest {

    @NotNull
    @Valid
    private Transformations transformations;

}
