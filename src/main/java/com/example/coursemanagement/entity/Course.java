package com.example.coursemanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Course entity.
 * Soft-deleted via the {@code deleted} flag — rows are never physically removed.
 * The instructor relationship has no cascade; removing an Instructor does not
 * remove their courses.
 */
@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    /** No cascade — instructor deletion must not remove courses. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    @Column(name = "course_image")
    private String courseImage;

    /**
     * Soft-delete flag.  Field name is {@code deleted}; Lombok generates
     * {@code isDeleted()} getter and {@code setDeleted()} setter, which is
     * consistent with Spring Data method-name derivation (findAllByDeletedFalse).
     */
    @Builder.Default
    @Column(name = "is_deleted", nullable = false)
    private boolean deleted = false;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
