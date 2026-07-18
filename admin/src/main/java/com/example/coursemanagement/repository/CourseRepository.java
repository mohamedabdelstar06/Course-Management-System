package com.example.coursemanagement.repository;

import com.example.coursemanagement.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    
    Page<Course> findAllByDeletedFalse(Pageable pageable);
    java.util.List<Course> findAllByDeletedFalse();
    Optional<Course> findByIdAndDeletedFalse(Long id);
}
