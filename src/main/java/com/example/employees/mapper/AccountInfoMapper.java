package com.example.employees.mapper;

import com.example.employees.dto.AccountInfoDTO;
import com.example.employees.model.AccountInfo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountInfoMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public AccountInfo convertToEntity(AccountInfoDTO accountInfoDTO) {
        return modelMapper.map(accountInfoDTO, AccountInfo.class);
    }
}
