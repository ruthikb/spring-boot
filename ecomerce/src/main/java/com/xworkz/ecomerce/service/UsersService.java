package com.xworkz.ecomerce.service;



import com.xworkz.ecomerce.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public interface UsersService {

    String saveUsers(List<UserDto> userDtos);

    ArrayList<String> updateUsers(List<UserDto> userDtos);

    String deleteUsers(List<Integer> ids);
}
