package com.xworkz.ecomerce.mapper;



import com.xworkz.ecomerce.dto.AddressDto;
import com.xworkz.ecomerce.mysql.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper  // okay impl for this is given by MapStructProcessor-> Processor(interface) given by java to process annotations
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(source = "postalCode",target = "postalCode")
    AddressEntity addressDtoToAddressEntity(AddressDto addressDto);

}
