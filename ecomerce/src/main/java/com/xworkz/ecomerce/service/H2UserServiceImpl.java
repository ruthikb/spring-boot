package com.xworkz.ecomerce.service;


import com.xworkz.ecomerce.dto.UserDto;
import com.xworkz.ecomerce.h2.entity.H2UserEntity;
import com.xworkz.ecomerce.h2.repository.H2UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class H2UserServiceImpl implements H2UserService {

    @Autowired
    private H2UserRepository h2UserRepository;

    @Override
    public boolean saveUser(UserDto userDto) {
        log.info("Attempting to save user: {}", userDto.getEmail());

        H2UserEntity h2UserEntity = new H2UserEntity();
        h2UserEntity.setEmail(userDto.getEmail());
        h2UserEntity.setName(userDto.getName());
        h2UserEntity.setPhoneNumber(userDto.getPhoneNumber());

        try {
            h2UserRepository.save(h2UserEntity);
            log.info("User saved successfully: {}", userDto.getEmail());
            return true;
        } catch (Exception e) {
            log.error("Error occurred while saving user: {}", userDto.getEmail(), e);
            return false;
        }
    }
}


