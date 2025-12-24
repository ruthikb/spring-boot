package com.xworkz.ecomerce.mysql.repository;


import com.xworkz.ecomerce.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    List<UserEntity> findByNameContainingIgnoreCase(String name);


}
