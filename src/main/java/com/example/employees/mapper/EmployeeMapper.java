package com.example.employees.mapper;


import com.example.employees.dto.EmployeeDTO;
import com.example.employees.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class EmployeeMapper {

        private static final ModelMapper modelMapper = new ModelMapper();

        public static EmployeeDTO mapToDTO(Employee employee) {
            return modelMapper.map(employee, EmployeeDTO.class);
        }
    }

