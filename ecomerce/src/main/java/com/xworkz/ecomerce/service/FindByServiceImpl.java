package com.xworkz.ecomerce.service;


import com.xworkz.ecomerce.dto.AddressDto;
import com.xworkz.ecomerce.dto.UserDto;
import com.xworkz.ecomerce.mysql.entity.AddressEntity;
import com.xworkz.ecomerce.mysql.entity.UserEntity;
import com.xworkz.ecomerce.mysql.repository.UserRepository;
import com.xworkz.ecomerce.service.FindByService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FindByServiceImpl implements FindByService {

   private final UserRepository userRepository;

    public FindByServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<List<AddressDto>> findByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name).stream()
                .map(userEntity -> userEntity.getAddresses().stream()
                        .map(addressEntity -> {
                            AddressDto addressDto = new AddressDto();
                            BeanUtils.copyProperties(addressEntity, addressDto);
                            return addressDto;
                        })
                        .collect(Collectors.toList())
                )
                .collect(Collectors.toList());
    }


    @Override
    public Page<UserDto> findAllUsers(Pageable pageable) {

        Page<UserEntity> all = userRepository.findAll(pageable);

        List<UserEntity> userEntities = all.get().toList();
        List<UserDto> userDtos = new ArrayList<>();

        userEntities.forEach(userEntity -> {
            List<AddressDto> addressDtos = new ArrayList<>();
            List<AddressEntity> addresses = userEntity.getAddresses();

            addresses.forEach(addressEntity -> {
                AddressDto addressDto = new AddressDto();
                BeanUtils.copyProperties(addressEntity,addressDto);
                addressDtos.add(addressDto);
            });
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity,userDto);
            userDto.setAddressDtos(addressDtos);
            userDtos.add(userDto);
        });
        return new PageImpl<>(userDtos,pageable,all.getTotalElements());
    }

    @Override
    public UserEntity findById(String id) {
        Optional<UserEntity> byId = userRepository.findById(Integer.valueOf(id));
        return byId.get();
    }
}
