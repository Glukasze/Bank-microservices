package com.glukasze.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(name = "ErrorResponse", description = "Data Transfer Object for API error responses")
public class ErrorResponseDto {

    @Schema(description = "API path where the error occurred", example = "/api/update")
    private String apiPath;

    @Schema(description = "HTTP status code representing the error", example = "404")
    private HttpStatus errorCode;

    @Schema(description = "Detailed error message", example = "Resource not found")
    private String errorMessage;

    @Schema(description = "Timestamp when the error occurred", example = "2023-10-01T12:00:00")
    private LocalDateTime errorTime;

}
