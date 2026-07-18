package com.example.coursemanagement.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Student profile — linked 1:1 to a User whose role is STUDENT.
 * Created automatically by AuthService during registration.
 */
@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
}
