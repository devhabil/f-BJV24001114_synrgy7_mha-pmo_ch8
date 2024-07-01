package com.examplechallengebinarc4.challengebinarc4.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User extends AbstrackFood implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(length = 255)
    private String username;

    @Column(name = "email_address", length = 255)
    private String emailAddress;

    @Column(name = "password", length = 255)
    private String password;

}
