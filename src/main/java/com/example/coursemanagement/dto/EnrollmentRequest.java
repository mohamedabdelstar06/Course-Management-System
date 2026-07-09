package com.example.coursemanagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** Request body for POST /api/enrollments. */
@Data
public class EnrollmentRequest {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Course ID is required")
    private Long courseId;
}
