package com.example.employees.mapper;

import com.example.employees.dto.ContractDTO;
import com.example.employees.model.Contract;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
    public class ContractMapper {

        private static final ModelMapper modelMapper = new ModelMapper();

        public Contract convertToEntity(ContractDTO contractDTO) {
            return modelMapper.map(contractDTO, Contract.class);
        }
    }

