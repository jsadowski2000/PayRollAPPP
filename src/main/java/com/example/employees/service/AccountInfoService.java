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

    

    public void deleteAccountInfo(UUID id) {
        accountInfoRepository.deleteById(id);
    }
}
