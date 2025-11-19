package com.xworkz.ecomerce.mysql.service;

import com.xworkz.ecomerce.mysql.dto.UserDto;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    String registerUser(UserDto userDto);

    UserDto getUserById(int id);

    UserDto updateUser(int id,UserDto userDto);

    boolean deleteUser(int id);

    boolean createUsers(@Valid List<UserDto> userdtos);

    ArrayList<String> updateUsers(List<UserDto> userdtos);



    String deleteUsers(List<Integer> ids);
}
