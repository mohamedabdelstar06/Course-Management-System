package com.example.coursemanagement.repository;

import com.example.coursemanagement.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    /** Used to detect duplicate enrolment before inserting (returns 409). */
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

    /** Returns all enrollments for a given student (for GET /enrollments/student/{id}). */
    List<Enrollment> findAllByStudentId(Long studentId);

    int countByCourseId(Long courseId);
}
