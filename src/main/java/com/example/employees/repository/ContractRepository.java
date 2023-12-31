package com.example.employees.repository;

import com.example.employees.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContractRepository extends JpaRepository<Contract, UUID> {

    Contract findByIdAndEmployeeId(UUID contractId, UUID employeeId);


}
