package com.xworkz.ecomerce.h2.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "H2UserEntity")
@Data
public class H2UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phoneNumber;

}
