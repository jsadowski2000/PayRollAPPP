package com.example.employees.service;

import com.example.employees.dto.EmployeeDTO;
import com.example.employees.mapper.EmployeeMapper;
import com.example.employees.model.AccountInfo;
import com.example.employees.model.Contract;
import com.example.employees.model.Employee;
import com.example.employees.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;



    public Employee saveEmployee(@RequestBody Employee employee) {
        Employee newEmployee = new Employee();
        newEmployee.setName(employee.getName());
        newEmployee.setSurname(employee.getSurname());
        newEmployee.setEmail(employee.getEmail());
        newEmployee.setLastUpdate(LocalDateTime.now());
        newEmployee.setCreateDate(new Date());

        return employeeRepository.save(newEmployee);
    }

    public List<EmployeeDTO> getAllEmployeesDTO() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public Employee getEmployeeById(UUID employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }


    public Employee updateEmployee(UUID employeeId, Employee updatedEmployee) {
        Employee existingEmployee = getEmployeeById(employeeId);

        if (existingEmployee == null) {
            return null;
        }

        if (updatedEmployee.getName() != null) {
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setLastUpdate(LocalDateTime.now());
        }

        if (updatedEmployee.getSurname() != null) {
            existingEmployee.setSurname(updatedEmployee.getSurname());
            existingEmployee.setLastUpdate(LocalDateTime.now());
        }

        if (updatedEmployee.getEmail() != null) {
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setLastUpdate(LocalDateTime.now());
        }

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(UUID employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public List<Contract> getContractsByEmployee(UUID employeeId) {
        Employee employee = getEmployeeById(employeeId);
        if (employee != null) {
            return employee.getContracts();
        }
        return Collections.emptyList();
    }

    public List<AccountInfo> getAccountsInfoByEmployee(UUID employeeId) {
        Employee employee = getEmployeeById(employeeId);
        if (employee != null) {
            return employee.getAccountsInfo();
        }
        return Collections.emptyList();
    }
}