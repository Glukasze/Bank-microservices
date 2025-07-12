package com.glukasze.accounts.controller;


import com.glukasze.accounts.constants.AccountsConstants;
import com.glukasze.accounts.dto.AccountsContactInfoDto;
import com.glukasze.accounts.dto.CustomerDto;
import com.glukasze.accounts.dto.ErrorResponseDto;
import com.glukasze.accounts.dto.ResponseDto;
import com.glukasze.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.glukasze.accounts.constants.AccountsConstants.MESSAGE_201;
import static com.glukasze.accounts.constants.AccountsConstants.STATUS_201;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(name = "CRUD REST APIs for accounts", description = "This controller provides CRUD operations for customer accounts")
public class AccountsController {

    private IAccountsService iAccountsService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

    public AccountsController(IAccountsService iAccountsService) {
        this.iAccountsService = iAccountsService;
    }

    @Operation(
            summary = "Create account",
            description = "This endpoint allows you to create a new customer account with the provided details")
    @ApiResponse(responseCode = "201", description = "Account created successfully")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(STATUS_201, MESSAGE_201));
    }

    @Operation(
            summary = "Fetch account",
            description = "This endpoint allows you to fetch an existing customer account by providing the mobile number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account fetched successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Failed to fetch account",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(
            @RequestParam
            @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber) {

        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(
            summary = "Update account",
            description = "This endpoint allows you to update an existing customer account with the provided details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account updated successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Failed to update account",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);

        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }
    }

    @Operation(
            summary = "Delete account",
            description = "This endpoint allows you to delete an existing customer account by providing the mobile number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account deleted successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Failed to delete account",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(
            @RequestParam
            @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber) {

        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);

        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }
    }

    @Operation(
            summary = "Get build version",
            description = "This endpoint returns the build version of the application")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Build version retrieved successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Failed to retrieve build version",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @GetMapping("/build-version")
    public ResponseEntity<String> getBuildVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @Operation(
            summary = "Get Java version",
            description = "This endpoint returns the Java version used to run the application")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Java version retrieved successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Failed to retrieve Java version",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary = "Get Maven version",
            description = "This endpoint returns the Maven version used to build the application")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maven version retrieved successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Failed to retrieve Maven version",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @GetMapping("/maven-version")
    public ResponseEntity<String> getMavenVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("MAVEN_HOME"));
    }

    @Operation(
            summary = "Get contact info",
            description = "This endpoint returns the contact information for the application")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contact info retrieved successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Failed to retrieve contact info",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }

}
