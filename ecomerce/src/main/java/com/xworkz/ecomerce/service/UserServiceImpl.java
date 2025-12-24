package com.xworkz.ecomerce.service;


import com.xworkz.ecomerce.dto.AddressDto;
import com.xworkz.ecomerce.dto.UserDto;
import com.xworkz.ecomerce.mapper.AddressMapper;
import com.xworkz.ecomerce.mysql.entity.AddressEntity;
import com.xworkz.ecomerce.mysql.entity.UserEntity;
import com.xworkz.ecomerce.mysql.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository repository;

    public UserServiceImpl(UserRepository userRepository){
        this.repository=userRepository;
    }

    @Override
    public String saveAndValidate(UserDto dto) {
        if(dto == null){
            return "nullError";
        }
        UserEntity entity = new UserEntity();
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        for (AddressDto addressDto : dto.getAddressDtos()) {
            AddressEntity addressEntity = AddressMapper.INSTANCE.addressDtoToAddressEntity(addressDto);
            entity.addAddress(addressEntity);
        }
        repository.save(entity);
        return "OK";
    }
    @Override
    @Cacheable("HarshaCache")
    public List<UserDto> findAll() {
        System.out.println("running inside findAll...");
        List<UserDto> dtos = new ArrayList<>();

        for(UserEntity entity : repository.findAll()){
            UserDto dto = new UserDto();
            BeanUtils.copyProperties(entity,dto);
            List<AddressDto> addressDtos = new ArrayList<>();
            for (AddressEntity addressEntity : entity.getAddresses()){
                AddressDto addressDto = new AddressDto();
                BeanUtils.copyProperties(addressEntity,addressDto);
                addressDtos.add(addressDto);
            }
            dto.setAddressDtos(addressDtos);
            dtos.add(dto);
        }
        return dtos ;
    }


    @Override
    @CacheEvict(value = "HarshaCache" , allEntries = true)
    public void deleteUser(int id) {
        repository.deleteById(id);
    }


    @CacheEvict(value ="HarshaCache" ,allEntries = true )
    @Override
    public String updateUser(UserDto dto) {

        Optional<UserEntity> exists = repository.findById(dto.getUpdateId());

        if (exists.isEmpty()){
            return "no id";
        }
       UserEntity userEntity = exists.get();

        List<AddressEntity> existingAddresses = userEntity.getAddresses();

        Map<Long , AddressEntity> addressEntityMap = userEntity.getAddresses().stream()
                .collect(Collectors.toMap(AddressEntity::getId, Function.identity()));

        for (AddressDto addressDto : dto.getAddressDtos()){
            if (addressDto.getUpdateAddressId() >0 && addressEntityMap.containsKey(addressDto.getUpdateAddressId()) ){
                existingAddresses.forEach(addressEntity -> {
                    setAddressEntity(addressDto, addressEntity, userEntity);
                });
                userEntity.setEmail(dto.getEmail());
                userEntity.setName(dto.getName());
                userEntity.setPhoneNumber(dto.getPhoneNumber());
                repository.save(userEntity);
            }
            else {
                AddressEntity addressEntity = new AddressEntity();
                setAddressEntity(addressDto, addressEntity, userEntity);
                userEntity.setEmail(dto.getEmail());
                userEntity.setName(dto.getName());
                userEntity.setPhoneNumber(dto.getPhoneNumber());
                repository.save(userEntity);
            }
        }
        return "ok";
    }

    private static void setAddressEntity(AddressDto addressDto, AddressEntity addressEntity, UserEntity userEntity) {
        addressEntity.setId( addressDto.getUpdateAddressId());
        addressEntity.setStreet(addressDto.getStreet());
        addressEntity.setCity(addressDto.getCity());
        addressEntity.setState(addressDto.getState());
        addressEntity.setPostalCode(addressDto.getPostalCode());
        addressEntity.setCountry(addressDto.getCountry());
        addressEntity.setLandmark(addressDto.getLandmark());
        addressEntity.setAddressType(addressDto.getAddressType());
        userEntity.addAddress(addressEntity);
    }

}
