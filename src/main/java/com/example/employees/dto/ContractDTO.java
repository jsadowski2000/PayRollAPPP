package com.example.employees.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data
public class ContractDTO {


        private UUID id;
        private String contractType;
        private double salary;
        private Date contractDate;
        private Date validityOfContract;

    }

