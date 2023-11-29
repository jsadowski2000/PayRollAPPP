package com.example.employees.service;

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

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Contract getContractById(UUID id) {
        return contractRepository.findById(id).orElse(null);
    }

    public Contract createContract(UUID employeeId, Contract body) {
        Employee employee = employeeService.getEmployeeById(employeeId);

        Contract newContract = new Contract();
        newContract.setContractType(body.getContractType());
        newContract.setSalary(body.getSalary());
        newContract.setContractDate(body.getContractDate());
        newContract.setValidityOfContract(body.getValidityOfContract());

        newContract.setEmployee(employee);

        Contract savedContract = contractRepository.save(newContract);

//        List<Contract> employeeContracts = employee.getContracts();
//        employee.setContracts(savedContract.getId());
//        employeeRepository.save(employee);

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
