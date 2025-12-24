package com.xworkz.ecomerce.service;


import com.xworkz.ecomerce.dto.UserDto;
import com.xworkz.ecomerce.h2.entity.H2UserEntity;
import com.xworkz.ecomerce.h2.repository.H2UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class H2UserServiceImpl implements H2UserService {

    @Autowired
    private H2UserRepository h2UserRepository ;

    @Override
    public boolean saveUser(UserDto userDto) {
        H2UserEntity h2UserEntity = new H2UserEntity();
        h2UserEntity.setEmail(userDto.getEmail());
        h2UserEntity.setName(userDto.getName());
        h2UserEntity.setPhoneNumber(userDto.getPhoneNumber());
        h2UserRepository.save(h2UserEntity);
        return true;
    }
}
