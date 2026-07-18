package com.example.coursemanagement.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Instructor profile — linked 1:1 to a User whose role is INSTRUCTOR.
 * Created automatically by AuthService during registration.
 * Deleting an Instructor does NOT cascade-delete their Courses.
 */
@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
}
