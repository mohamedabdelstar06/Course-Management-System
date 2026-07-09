package com.example.coursemanagement.service;

import com.example.coursemanagement.dto.EnrollmentRequest;
import com.example.coursemanagement.dto.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {

    
    EnrollmentResponse enroll(EnrollmentRequest request);

    List<EnrollmentResponse> getByStudent(Long studentId);

    /** Hard-deletes the enrollment row (unenrol). */
    void unenroll(Long id);
}
