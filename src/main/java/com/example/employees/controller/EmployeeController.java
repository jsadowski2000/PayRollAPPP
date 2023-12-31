package com.example.employees.controller;

import com.example.employees.dto.EmployeeDTO;
import com.example.employees.model.AccountInfo;
import com.example.employees.model.Employee;
import com.example.employees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController

public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployeesDTO();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable UUID employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable UUID employeeId,
            @RequestBody Employee updatedEmployee) {

        Employee updated = employeeService.updateEmployee(employeeId, updatedEmployee);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable UUID employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @GetMapping("/{employeeId}/accounts-info")
    public List<AccountInfo> getAccountsInfoByEmployee(@PathVariable UUID employeeId) {
        return employeeService.getAccountsInfoByEmployee(employeeId);
    }
}

