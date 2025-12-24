package com.xworkz.ecomerce.wrapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.xworkz.ecomerce.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Data
@NoArgsConstructor
public class UserDataWrapper {

    private List<UserDto> userDtos;

    @JsonCreator
    public UserDataWrapper(@JsonProperty("userDtos") List<UserDto> userDtos) {
        this.userDtos = userDtos;
    }

}
