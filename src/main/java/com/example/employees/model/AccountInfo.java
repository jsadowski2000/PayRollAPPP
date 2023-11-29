package com.example.employees.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.UUID;

@Data
@Entity@Table(name = "AccountInfo")

public class AccountInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id")
    public UUID id;


    @Column(name= "account_type")
    public String accountType;

    @Column(name="account_number")
    public long accountNumber;


    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;


}