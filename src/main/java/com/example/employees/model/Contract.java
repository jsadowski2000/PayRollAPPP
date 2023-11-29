package com.example.employees.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.UUID;

@Data
@Entity@Table(name = "CONTRACT")

public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column (name= "contract_id")
    private UUID id;

    @Column (name = "contract_type")
    private String contractType;

    @Column (name = "salary")
    private double salary;

    @Column (name = "contract_date")
    private Date contractDate;


    @Column (name = "validity_of_the_contract")
    private Date validityOfContract;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

}
