package com.example.employees.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data

public class EmployeeDTO {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private Date createDate;
    private LocalDateTime lastUpdate;
    private List<ContractDTO> contracts;
    private List<AccountInfoDTO> accountsInfo;
}
