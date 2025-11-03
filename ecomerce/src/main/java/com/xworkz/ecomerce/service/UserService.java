package com.xworkz.ecomerce.service;

import com.xworkz.ecomerce.dto.UserDto;

import java.util.List;

public interface UserService {

    String registerUser(UserDto userDto);

    UserDto getUserById(int id);

    UserDto updateUser(int id,UserDto userDto);

    boolean deleteUser(int id);

    List<UserDto> createUsers(List<UserDto> userdtos);
}
