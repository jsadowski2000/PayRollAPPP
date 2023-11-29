package com.example.employees.service;

import com.example.employees.model.AccountInfo;
import com.example.employees.model.Contract;
import com.example.employees.model.Employee;
import com.example.employees.repository.AccountInfoRepository;
import com.example.employees.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<AccountInfo> getAllAccountInfo() {
        return accountInfoRepository.findAll();
    }

    public AccountInfo getAccountInfoById(UUID id) {
        return accountInfoRepository.findById(id).orElse(null);
    }

    public AccountInfo createAccountInfo(UUID employeeId, AccountInfo body) {
        Employee employee = employeeService.getEmployeeById(employeeId);

        AccountInfo newAccountInfo = new AccountInfo();
        newAccountInfo.setAccountType(body.getAccountType());
        newAccountInfo.setAccountNumber(body.getAccountNumber());
        newAccountInfo.setEmployee(employee);

        AccountInfo savedAccountInfo = accountInfoRepository.save(newAccountInfo);
        return savedAccountInfo;

    }

    public AccountInfo updateAccountInfo(UUID id, AccountInfo updatedAccountInfo) {
        AccountInfo accountInfo = accountInfoRepository.findById(id).orElse(null);
        if (accountInfo != null) {
            // Aktualizacja pól informacji o koncie
            accountInfo.setAccountType(updatedAccountInfo.getAccountType());
            accountInfo.setAccountNumber(updatedAccountInfo.getAccountNumber());

            // Aktualizacja relacji z pracownikiem
            accountInfo.setEmployee(updatedAccountInfo.getEmployee());

            return accountInfoRepository.save(accountInfo);
        }
        return null;
    }

    public void deleteAccountInfo(UUID id) {
        accountInfoRepository.deleteById(id);
    }
}
