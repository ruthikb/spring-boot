package com.xworkz.ecomerce.mysql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "address_entity")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String landmark;
    private String addressType;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private UserEntity user;
}
