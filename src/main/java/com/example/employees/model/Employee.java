package com.example.employees.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity@Table(name = "EMPLOYEE")

public class Employee {
@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column (name= "employee_id")
    private UUID id;

    @Column(name = "name", length = 50)
    private String name;
    @Column(name = "surname", length = 50)
    private String surname;

    @Column(name = "email", length = 254)
    private String email;


    @Column(name = "create_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createDate;

    @Column(name="last_update", updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private List<Contract> contracts = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private List<AccountInfo> accountsInfo = new ArrayList<>();


}




