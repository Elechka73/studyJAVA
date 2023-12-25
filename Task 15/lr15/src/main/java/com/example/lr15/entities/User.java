package com.example.lr15.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;

    private String password;

    private String name;

    private Boolean enabled;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Authority authority;
}