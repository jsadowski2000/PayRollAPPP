package com.example.employees.controller;

import com.example.employees.dto.ContractDTO;
import com.example.employees.model.Contract;
import com.example.employees.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contracts")
public class ContractController {
    @Autowired
    private ContractService contractService;

    @PostMapping("/{employeeId}")
    public ResponseEntity<Contract> createContract(
            @PathVariable UUID employeeId,
            @RequestBody ContractDTO contractDTO) {

        Contract createdContract = contractService.createContract(employeeId, contractDTO);

        if (createdContract != null) {
            return new ResponseEntity<>(createdContract, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{employeeId}/{contractId}")
    public ResponseEntity<Contract> updateContract(
            @PathVariable UUID employeeId,
            @PathVariable UUID contractId,
            @RequestBody ContractDTO updatedContractDTO) {

        Contract updatedContract = contractService.updateContract(employeeId, contractId, updatedContractDTO);

        if (updatedContract != null) {
            return ResponseEntity.ok(updatedContract);
        } else {
            return ResponseEntity.notFound().build(); // Możesz dostosować status odpowiedzi do swoich potrzeb
        }
    }

    @DeleteMapping("/{employeeId}/{contractId}")
    public ResponseEntity<Void> deleteContract(
            @PathVariable UUID employeeId,
            @PathVariable UUID contractId) {

        contractService.deleteContract(employeeId, contractId);

        return ResponseEntity.noContent().build();
    }
}
