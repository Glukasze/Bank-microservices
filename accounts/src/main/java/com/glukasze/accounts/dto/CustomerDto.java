package com.glukasze.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Data Transfer Object for Customer and its account")
public class CustomerDto {

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 5, max = 30, message = "Name must be between 5 and 30 characters")
    @Schema(description = "Name of the customer", example = "John Smith")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Schema(description = "Email of the customer", example = "John.Smith@Gmail.com")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(description = "Mobile number of the customer", example = "1234567890")
    private String mobileNumber;

    @Schema(description = "Accounts associated with the customer", implementation = AccountsDto.class)
    private AccountsDto accountsDto;

}
