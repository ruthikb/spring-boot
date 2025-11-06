package com.xworkz.ecomerce.service.impl;
import com.xworkz.ecomerce.dto.UserDto;
import com.xworkz.ecomerce.entity.UserEntity;
import com.xworkz.ecomerce.repositry.UserRepo;
import com.xworkz.ecomerce.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public boolean createUsers(List<UserDto> userdtos) {
        if (userdtos == null || userdtos.isEmpty()) {
            return false;
        }
        List<UserEntity> userEntities = new ArrayList<>();
        for(UserDto userDto : userdtos){
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(userDto,userEntity);
            userEntities.add(userEntity);
        }
        userRepo.saveAll(userEntities);
        return  true;
    }

    @Override
    public ArrayList<String> updateUsers(List<UserDto> userdtos) {
        ArrayList<String> ids = new  ArrayList<>();
        for (UserDto dto:userdtos){
            Optional<UserEntity> userEntity = userRepo.findById(dto.getId());
            if (userEntity.isPresent()) {
                UserEntity entity = userEntity.get();
                entity.setFirstName(dto.getFirstName());
                entity.setLastName(dto.getLastName());
                entity.setEmail(dto.getEmail());
                entity.setPhoneNo(dto.getPhoneNo());
                entity.setAddress(dto.getAddress());
                entity.setPassword(dto.getPassword());

                UserEntity saved = userRepo.save(entity);
                ids.add("Updated user with ID: " + saved.getId());
            }
        }
        return null;
    }

    @Override
    public String deleteUsers(List<Integer> ids) {
        for (Integer id : ids) {
            if (userRepo.existsById(id)) {
                userRepo.deleteById(id);
            }
        }

        return "";
    }



}
