package com.example.employees.service;

import com.example.employees.dto.ContractDTO;
import com.example.employees.mapper.ContractMapper;
import com.example.employees.model.Contract;
import com.example.employees.model.Employee;
import com.example.employees.repository.ContractRepository;
import com.example.employees.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ContractMapper contractMapper;

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Contract getContractById(UUID id) {
        return contractRepository.findById(id).orElse(null);
    }

    public Contract createContract(UUID employeeId, ContractDTO contractDTO) {
        Employee employee = employeeService.getEmployeeById(employeeId);

        if (employee == null) {
            // Obsługa błędu: pracownik nie istnieje
            return null;
        }

        Contract newContract = contractMapper.convertToEntity(contractDTO);
        newContract.setEmployee(employee);

        Contract savedContract = contractRepository.save(newContract);

        // Możesz dodać inne operacje lub logikę tutaj

        return savedContract;
    }


    public Contract updateContract(UUID employeeId, UUID contractId, ContractDTO updatedContractDTO) {
        Employee employee = employeeService.getEmployeeById(employeeId);

        if (employee == null) {
            // Obsługa błędu: pracownik nie istnieje
            return null;
        }

        Contract existingContract = contractRepository.findByIdAndEmployeeId(contractId, employeeId);

        if (existingContract == null) {
            // Obsługa błędu: umowa nie istnieje lub nie należy do danego pracownika
            return null;
        }

        // Aktualizuj istniejącą umowę tylko jeśli dane są dostarczone
        if (updatedContractDTO.getContractType() != null) {
            existingContract.setContractType(updatedContractDTO.getContractType());
        }
        if (updatedContractDTO.getSalary() != 0.0) {
            existingContract.setSalary(updatedContractDTO.getSalary());
        }
        if (updatedContractDTO.getContractDate() != null) {
            existingContract.setContractDate(updatedContractDTO.getContractDate());
        }
        if (updatedContractDTO.getValidityOfContract() != null) {
            existingContract.setValidityOfContract(updatedContractDTO.getValidityOfContract());
        }

        Contract updatedContract = contractRepository.save(existingContract);

        // Możesz dodać inne operacje lub logikę tutaj

        return updatedContract;
    }


    public void deleteContract(UUID employeeId, UUID contractId) {
        Employee employee = employeeService.getEmployeeById(employeeId);

        if (employee == null) {
            // Obsługa błędu: pracownik nie istnieje
            return;
        }

        Contract existingContract = contractRepository.findByIdAndEmployeeId(contractId, employeeId);

        if (existingContract == null) {
            // Obsługa błędu: umowa nie istnieje lub nie należy do danego pracownika
            return;
        }

        // Usuń umowę
        contractRepository.delete(existingContract);


    }
}
