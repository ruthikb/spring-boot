package com.xworkz.ecomerce.h2.mapper;

import com.xworkz.ecomerce.h2.dto.CustomerDto;
import com.xworkz.ecomerce.h2.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")

    CustomerEntity dtoToEntity(CustomerDto dto);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    
    CustomerDto auditLogEntityToAuditLogDto(CustomerEntity entity);



}
