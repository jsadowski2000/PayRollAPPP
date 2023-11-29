package com.example.employees.controller;

import com.example.employees.model.AccountInfo;
import com.example.employees.model.Contract;
import com.example.employees.service.AccountInfoService;
import com.example.employees.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/accountinfo")
public class AccountInfoController {
    @Autowired
    private AccountInfoService accountInfoService;

    @PostMapping("/{employeeId}")
    public AccountInfo createContract(@PathVariable UUID employeeId, @RequestBody AccountInfo body) {
        return accountInfoService.createAccountInfo(employeeId, body);
    }

}