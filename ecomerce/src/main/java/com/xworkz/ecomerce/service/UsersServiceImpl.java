package com.xworkz.ecomerce.service;


import com.xworkz.ecomerce.dto.AddressDto;
import com.xworkz.ecomerce.dto.UserDto;
import com.xworkz.ecomerce.mysql.entity.AddressEntity;
import com.xworkz.ecomerce.mysql.entity.UserEntity;
import com.xworkz.ecomerce.mysql.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
@Slf4j
@Service
public class UsersServiceImpl implements UsersService{

    private final UserRepository repository;

    public UsersServiceImpl(UserRepository userRepository){
        this.repository=userRepository;
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


    @Override
    public String saveUsers(List<UserDto> userDtos) {
        log.info("Running saveUsers in UsersServiceImpl");
        List<UserEntity> userEntities = new ArrayList<>();
        for(UserDto dto : userDtos){
            UserEntity entity = new UserEntity();
            entity.setPhoneNumber(dto.getPhoneNumber());
            entity.setEmail(dto.getEmail());
            entity.setName(dto.getName());
            for (AddressDto addressDto : dto.getAddressDtos()) {
                AddressEntity addressEntity = new AddressEntity();
                setAddressEntity(addressDto,addressEntity,entity);
            }
            userEntities.add(entity);
        }
        List<UserEntity> userEntities1 = repository.saveAll(userEntities);
        if (userEntities1.isEmpty()){
            log.warn("No users saved in the database");
            return "notOK";
        }
        log.info("Users saved successfully in the database");
        return "OK";
    }

    @Override
    public ArrayList<String> updateUsers(List<UserDto> userDtos) {
        log.info("Running updateUsers in UsersServiceImpl");
        ArrayList<String> ids = new  ArrayList<>();
        for(UserDto dto : userDtos){
            Optional<UserEntity> exists = repository.findById(dto.getUpdateId());
            if (exists.isEmpty()){
                log.warn("User with ID {} does not exist", dto.getUpdateId());
                ids.add(String.valueOf(dto.getUpdateId()));
                break;
            }else {
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
                        log.debug("User with ID {} updated successfully", dto.getUpdateId());
                    }
                    else {
                        AddressEntity addressEntity = new AddressEntity();
                        setAddressEntity(addressDto, addressEntity, userEntity);
                        userEntity.setEmail(dto.getEmail());
                        userEntity.setName(dto.getName());
                        userEntity.setPhoneNumber(dto.getPhoneNumber());
                        repository.save(userEntity);
                        log.debug("User with ID {} updated successfully with new address", dto.getUpdateId());
                    }
                }
            }
        }
        log.info("Completed updateUsers in UsersServiceImpl");
        return ids;
    }

    @Override
    public String deleteUsers(List<Integer> ids) {
        log.info("Running deleteUsers in UsersServiceImpl");
        if (ids == null){
            log.warn("No IDs provided for deletion");
            return"Null";
        }
        ArrayList<String> notDeleted = new  ArrayList<>();
        for (Integer id : ids){
            Optional<UserEntity> exists = repository.findById(id);
            if (exists.isEmpty()){
                log.warn("User with ID {} does not exist for deletion", id);
                notDeleted.add(String.valueOf(id));
                break;
            }
            repository.deleteById(id);
            log.debug("User with ID {} deleted successfully", id);
        }
        log.info("Completed deleteUsers in UsersServiceImpl");
        return notDeleted.toString();
    }
}
