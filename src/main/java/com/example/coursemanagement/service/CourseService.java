package com.example.coursemanagement.service;

import com.example.coursemanagement.dto.CourseRequest;
import com.example.coursemanagement.dto.CourseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    CourseResponse create(CourseRequest request);
    Page<CourseResponse> findAll(Pageable pageable);
    CourseResponse findById(Long id);
    CourseResponse update(Long id, CourseRequest request);
    /** Sets isDeleted = true.  Row is never physically removed. */
    void softDelete(Long id);
}
