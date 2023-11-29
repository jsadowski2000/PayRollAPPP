package com.example.employees.repository;

import com.example.employees.model.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountInfoRepository extends JpaRepository<AccountInfo, UUID> {
}
