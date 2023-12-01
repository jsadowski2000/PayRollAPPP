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


    public Contract updateContract(UUID id, Contract updatedContract) {
        Contract contract = contractRepository.findById(id).orElse(null);
        if (contract != null) {
            // Aktualizacja pól umowy
            contract.setContractType(updatedContract.getContractType());
            contract.setSalary(updatedContract.getSalary());
            contract.setValidityOfContract(updatedContract.getValidityOfContract());

            // Aktualizacja relacji z pracownikiem
            contract.setEmployee(updatedContract.getEmployee());

            return contractRepository.save(contract);
        }
        return null;
    }

    public void deleteContract(UUID id) {
        contractRepository.deleteById(id);
    }
}
