package com.xworkz.ecomerce.mysql.repository;

import com.xworkz.ecomerce.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<UserEntity, Integer> {
}
