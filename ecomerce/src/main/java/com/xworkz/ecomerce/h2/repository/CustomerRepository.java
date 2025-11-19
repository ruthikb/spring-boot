package com.xworkz.ecomerce.h2.repository;

import com.xworkz.ecomerce.h2.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {}
