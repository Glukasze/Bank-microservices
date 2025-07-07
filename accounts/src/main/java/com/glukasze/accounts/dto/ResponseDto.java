package com.glukasze.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Response", description = "Data Transfer Object for API responses")
public class ResponseDto {

    @Schema(description = "Status code of the response")
    private String statusCode;

    @Schema(description = "Message providing additional information about the response")
    private String statusMsg;

}
