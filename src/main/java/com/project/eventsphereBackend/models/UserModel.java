package com.project.eventsphereBackend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    public String username;

    @Column(nullable = false,unique = true)
    public String email;

    @Column(nullable = false)
    public String password;

    @Column(nullable = false)
    private Boolean isAdmin;
}
