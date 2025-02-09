package com.pablodev.imageprocessing.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private HttpStatus status;
    private List<String> errors;
}
