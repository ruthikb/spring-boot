package com.xworkz.ecomerce.service.impl;

import com.xworkz.ecomerce.dto.UserDto;
import com.xworkz.ecomerce.entity.UserEntity;
import com.xworkz.ecomerce.repositry.UserRepo;
import com.xworkz.ecomerce.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;


    @Override
    public String registerUser(UserDto userDto) {
        boolean isVaild = false;
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        userRepo.save(userEntity);
        return "true";
    }

    @Override
    public UserDto getUserById(int id) {
        Optional<UserEntity> userEntity = userRepo.findById(id);
        if (userEntity.isEmpty()) {
            return null;


        }
        UserEntity entity = userEntity.get();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(entity, userDto);
        return userDto;

    }


    @Override
    public UserDto updateUser(int id, UserDto userDto) {
        Optional<UserEntity> userEntity = userRepo.findById(id);
        UserEntity entity = userEntity.get();
        entity.setFirstName(userDto.getFirstName());
        entity.setLastName(userDto.getLastName());
        entity.setEmail(userDto.getEmail());
        entity.setPhoneNo(userDto.getPhoneNo());
        entity.setAddress(userDto.getAddress());
        entity.setPassword(userDto.getPassword());

        UserEntity saved = userRepo.save(entity);
        UserDto updatedDto = new UserDto();
        BeanUtils.copyProperties(saved, updatedDto);
        return updatedDto;
    }

    @Override
    public boolean deleteUser(int id) {
        if (!userRepo.existsById(id)) {
            return false;
        }
        userRepo.deleteById(id);
        return true;
    }


    @Override
    public List<UserDto> createUsers(List<UserDto> userdtos) {
        return userRepo.saveAll(userdtos.stream().map(dto -> {
                            UserEntity entity = new UserEntity();
                            BeanUtils.copyProperties(dto, entity);
                            return entity;
                        })
                        .collect(Collectors.toList())).stream().map(entity -> {
                    UserDto dto = new UserDto();
                    BeanUtils.copyProperties(entity, dto);
                    return dto;
                }).collect(Collectors.toList());
    }


}
