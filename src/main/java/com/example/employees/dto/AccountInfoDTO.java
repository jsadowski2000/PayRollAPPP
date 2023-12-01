package com.example.employees.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class AccountInfoDTO {
    private UUID id;
    private String accountType;
    private long accountNumber;
}
