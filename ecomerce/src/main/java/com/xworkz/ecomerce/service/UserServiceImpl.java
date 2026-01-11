package com.xworkz.ecomerce.service;

import com.xworkz.ecomerce.dto.AddressDto;
import com.xworkz.ecomerce.dto.UserDto;
import com.xworkz.ecomerce.mapper.AddressMapper;
import com.xworkz.ecomerce.mysql.entity.AddressEntity;
import com.xworkz.ecomerce.mysql.entity.UserEntity;
import com.xworkz.ecomerce.mysql.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository userRepository) {
        this.repository = userRepository;
        log.info("UserServiceImpl initialized");
    }

    @Override
    public String saveAndValidate(UserDto dto) {
        log.info("saveAndValidate() started");

        if (dto == null) {
            log.warn("UserDto is null");
            return "nullError";
        }

        UserEntity entity = new UserEntity();
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());

        log.debug("Saving user: name={}, email={}", dto.getName(), dto.getEmail());

        for (AddressDto addressDto : dto.getAddressDtos()) {
            AddressEntity addressEntity =
                    AddressMapper.INSTANCE.addressDtoToAddressEntity(addressDto);
            entity.addAddress(addressEntity);

            log.debug("Added address: city={}, type={}",
                    addressDto.getCity(), addressDto.getAddressType());
        }

        repository.save(entity);
        log.info("User saved successfully with phoneNumber={}", dto.getPhoneNumber());

        return "OK";
    }

    @Override
    @Cacheable("KruthikCache")
    public List<UserDto> findAll() {
        log.info("findAll() called - fetching users");
        System.out.println("running inside findAll...");

        List<UserDto> dtos = new ArrayList<>();

        List<UserEntity> entities = repository.findAll();
        log.debug("Total users fetched from DB: {}", entities.size());

        for (UserEntity entity : entities) {
            UserDto dto = new UserDto();
            BeanUtils.copyProperties(entity, dto);

            List<AddressDto> addressDtos = new ArrayList<>();
            for (AddressEntity addressEntity : entity.getAddresses()) {
                AddressDto addressDto = new AddressDto();
                BeanUtils.copyProperties(addressEntity, addressDto);
                addressDtos.add(addressDto);
            }

            dto.setAddressDtos(addressDtos);
            dtos.add(dto);
        }

        log.info("findAll() completed, returning {} users", dtos.size());
        return dtos;
    }

    @Override
    @CacheEvict(value = "KruthikCache", allEntries = true)
    public void deleteUser(int id) {
        log.info("deleteUser() called with id={}", id);
        repository.deleteById(id);
        log.info("User deleted successfully with id={}", id);
    }

    @Override
    @CacheEvict(value = "KruthikCache", allEntries = true)
    public String updateUser(UserDto dto) {
        log.info("updateUser() started for userId={}", dto.getUpdateId());

        Optional<UserEntity> exists = repository.findById(dto.getUpdateId());

        if (exists.isEmpty()) {
            log.warn("User not found for id={}", dto.getUpdateId());
            return "no id";
        }

        UserEntity userEntity = exists.get();
        log.debug("User found: id={}, email={}", userEntity.getId(), userEntity.getEmail());

        Map<Long, AddressEntity> addressEntityMap =
                userEntity.getAddresses().stream()
                        .collect(Collectors.toMap(AddressEntity::getId, Function.identity()));

        for (AddressDto addressDto : dto.getAddressDtos()) {

            if (addressDto.getUpdateAddressId() > 0 &&
                    addressEntityMap.containsKey(addressDto.getUpdateAddressId())) {

                log.debug("Updating existing address id={}", addressDto.getUpdateAddressId());

                AddressEntity addressEntity =
                        addressEntityMap.get(addressDto.getUpdateAddressId());
                setAddressEntity(addressDto, addressEntity, userEntity);

            } else {
                log.debug("Adding new address for userId={}", userEntity.getId());
                AddressEntity addressEntity = new AddressEntity();
                setAddressEntity(addressDto, addressEntity, userEntity);
            }
        }

        userEntity.setEmail(dto.getEmail());
        userEntity.setName(dto.getName());
        userEntity.setPhoneNumber(dto.getPhoneNumber());

        repository.save(userEntity);
        log.info("User updated successfully for id={}", dto.getUpdateId());

        return "ok";
    }

    private static void setAddressEntity(
            AddressDto addressDto,
            AddressEntity addressEntity,
            UserEntity userEntity) {

        addressEntity.setId(addressDto.getUpdateAddressId());
        addressEntity.setStreet(addressDto.getStreet());
        addressEntity.setCity(addressDto.getCity());
        addressEntity.setState(addressDto.getState());
        addressEntity.setPostalCode(addressDto.getPostalCode());
        addressEntity.setCountry(addressDto.getCountry());
        addressEntity.setLandmark(addressDto.getLandmark());
        addressEntity.setAddressType(addressDto.getAddressType());

        userEntity.addAddress(addressEntity);
    }
}
