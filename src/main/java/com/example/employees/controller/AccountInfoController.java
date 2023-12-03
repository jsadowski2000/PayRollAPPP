package com.example.employees.controller;

import com.example.employees.dto.AccountInfoDTO;
import com.example.employees.dto.ContractDTO;
import com.example.employees.model.AccountInfo;
import com.example.employees.model.Contract;
import com.example.employees.service.AccountInfoService;
import com.example.employees.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/accountinfo")
public class AccountInfoController {
    @Autowired
    private AccountInfoService accountInfoService;

    @PostMapping("/{employeeId}")
    public ResponseEntity<AccountInfo> createAccountInfo(
            @PathVariable UUID employeeId,
            @RequestBody AccountInfoDTO accountInfoDTO) {

        AccountInfo createdAccountInfo = accountInfoService.createAccountInfo(employeeId, accountInfoDTO);

        if (createdAccountInfo != null) {
            return new ResponseEntity<>(createdAccountInfo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{employeeId}/{accountInfoId}")
    public ResponseEntity<AccountInfo> updateAccountInfo(
            @PathVariable UUID employeeId,
            @PathVariable UUID accountInfoId,
            @RequestBody AccountInfoDTO updatedAccountInfoDTO) {

        AccountInfo updatedAccountInfo = accountInfoService.updateAccountInfo(employeeId, accountInfoId, updatedAccountInfoDTO);

        if (updatedAccountInfo != null) {
            return ResponseEntity.ok(updatedAccountInfo);
        } else {
            return ResponseEntity.notFound().build(); // Możesz dostosować status odpowiedzi do swoich potrzeb
        }
    }

    @DeleteMapping("/{employeeId}/{accountInfoId}")
    public ResponseEntity<Void> deleteContract(
            @PathVariable UUID employeeId,
            @PathVariable UUID accountInfoId) {

        accountInfoService.deleteAccountInfo(employeeId, accountInfoId);

        return ResponseEntity.noContent().build();
    }
}