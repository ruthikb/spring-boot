package com.xworkz.ecomerce.service;


import com.xworkz.ecomerce.dto.AddressDto;
import com.xworkz.ecomerce.dto.UserDto;
import com.xworkz.ecomerce.mysql.entity.AddressEntity;
import com.xworkz.ecomerce.mysql.entity.UserEntity;
import com.xworkz.ecomerce.mysql.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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
            return "notOK";
        }
        return "OK";
    }

    @Override
    public ArrayList<String> updateUsers(List<UserDto> userDtos) {
        ArrayList<String> ids = new  ArrayList<>();
        for(UserDto dto : userDtos){
            Optional<UserEntity> exists = repository.findById(dto.getUpdateId());
            if (exists.isEmpty()){
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
            }
        }
        return ids;
    }

    @Override
    public String deleteUsers(List<Integer> ids) {
        if (ids == null){
            return"Null";
        }
        ArrayList<String> notDeleted = new  ArrayList<>();
        for (Integer id : ids){
            Optional<UserEntity> exists = repository.findById(id);
            if (exists.isEmpty()){
                notDeleted.add(String.valueOf(id));
                break;
            }
            repository.deleteById(id);
        }
        return notDeleted.toString();
    }
}
