package com.example.coursemanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Join entity representing the Student ↔ Course many-to-many relationship.
 * A unique constraint on (student_id, course_id) prevents duplicate enrolments
 * at the DB level; the service layer enforces this with a 409 before hitting
 * the constraint.
 */
@Entity
@Table(
    name = "enrollments",
    uniqueConstraints = @UniqueConstraint(
        name = "uq_enrollment_student_course",
        columnNames = {"student_id", "course_id"}
    )
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime enrollmentDate;
}
