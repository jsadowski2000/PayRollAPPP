package com.example.employees.controller;

import com.example.employees.model.Contract;
import com.example.employees.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {
    @Autowired
    private ContractService contractService;

    @PostMapping("/{employeeId}")
    public Contract createContract(@PathVariable UUID employeeId, @RequestBody Contract body) {
        return contractService.createContract(employeeId, body);
    }

}
