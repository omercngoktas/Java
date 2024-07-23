package com.omercngoktas.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "AccountNumber cannot be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "AccountNumber must be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "AccountType cannot be a null or empty")
    private String accountType;

    @NotEmpty(message = "BranchAddress cannot be a null or empty")
    private String branchAddress;

}
