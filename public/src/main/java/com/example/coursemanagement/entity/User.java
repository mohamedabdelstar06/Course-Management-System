package com.example.coursemanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import com.example.coursemanagement.enums.UserRole;

/**
 * Core identity entity — stores credentials and role.
 * Created automatically by AuthService during registration.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    /** BCrypt-hashed password — never store or return plaintext. */
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserRole role;

    @Column(name = "profile_image")
    private String profileImage;
}
