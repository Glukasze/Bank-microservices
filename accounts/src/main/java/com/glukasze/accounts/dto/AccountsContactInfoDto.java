package com.glukasze.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "accounts")
public class AccountsContactInfoDto {

    private String message;
    private Map<String, String> contactDetails;
}
