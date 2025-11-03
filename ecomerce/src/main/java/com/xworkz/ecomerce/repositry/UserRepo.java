package com.xworkz.ecomerce.repositry;

import com.xworkz.ecomerce.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<UserEntity, Integer> {
}
