package com.example.employees.service;

import com.example.employees.dto.AccountInfoDTO;
import com.example.employees.dto.ContractDTO;
import com.example.employees.mapper.AccountInfoMapper;
import com.example.employees.mapper.ContractMapper;
import com.example.employees.model.AccountInfo;
import com.example.employees.model.Contract;
import com.example.employees.model.Employee;
import com.example.employees.repository.AccountInfoRepository;
import com.example.employees.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountInfoService {
    @Autowired
    private AccountInfoRepository accountInfoRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AccountInfoMapper accountInfoMapper;

    public List<AccountInfo> getAllAccountInfo() {
        return accountInfoRepository.findAll();
    }

    public AccountInfo getAccountInfoById(UUID id) {
        return accountInfoRepository.findById(id).orElse(null);
    }

    public AccountInfo createAccountInfo(UUID employeeId, AccountInfoDTO accountInfoDTO) {
        Employee employee = employeeService.getEmployeeById(employeeId);

        if (employee == null) {
            // Obsługa błędu: pracownik nie istnieje
            return null;
        }

        AccountInfo newAccountInfo = accountInfoMapper.convertToEntity(accountInfoDTO);
        newAccountInfo.setEmployee(employee);

        AccountInfo savedAccountInfo = accountInfoRepository.save(newAccountInfo);

        // Możesz dodać inne operacje lub logikę tutaj

        return savedAccountInfo;
    }
    public AccountInfo updateAccountInfo(UUID employeeId, UUID accountInfoId, AccountInfoDTO updatedAccountInfoDTO) {
        Employee employee = employeeService.getEmployeeById(employeeId);

        if (employee == null) {
            // Obsługa błędu: pracownik nie istnieje
            return null;
        }

        AccountInfo existingAccountInfo = accountInfoRepository.findByIdAndEmployeeId(accountInfoId, employeeId);

        if (existingAccountInfo == null) {
            // Obsługa błędu: accountInfo nie istnieje lub nie należy do danego pracownika
            return null;
        }

        // Aktualizuj istniejące konto tylko jeśli dane są dostarczone
        if (updatedAccountInfoDTO.getAccountType() != null) {
            existingAccountInfo.setAccountType(updatedAccountInfoDTO.getAccountType());
        }
        if (updatedAccountInfoDTO.getAccountNumber() != 0) {
            existingAccountInfo.setAccountNumber(updatedAccountInfoDTO.getAccountNumber());
        }

        AccountInfo updatedAccountInfo = accountInfoRepository.save(existingAccountInfo);

        // Możesz dodać inne operacje lub logikę tutaj

        return updatedAccountInfo;
    }


    public void deleteAccountInfo(UUID employeeId, UUID accountInfoId) {
        Employee employee = employeeService.getEmployeeById(employeeId);

        if (employee == null) {
            // Obsługa błędu: pracownik nie istnieje
            return;
        }

        AccountInfo existingAccountInfo = accountInfoRepository.findByIdAndEmployeeId(accountInfoId, employeeId);

        if (existingAccountInfo == null) {
            // Obsługa błędu: umowa nie istnieje lub nie należy do danego pracownika
            return;
        }

        // Usuń umowę
        accountInfoRepository.delete(existingAccountInfo);


    }
}

    


