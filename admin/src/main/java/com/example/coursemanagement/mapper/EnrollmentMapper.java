package com.example.coursemanagement.mapper;

import com.example.coursemanagement.dto.EnrollmentResponse;
import com.example.coursemanagement.entity.Enrollment;
import org.springframework.stereotype.Component;

/** Maps Enrollment entity → EnrollmentResponse DTO. */
@Component
public class EnrollmentMapper {

    public EnrollmentResponse toResponse(Enrollment enrollment) {
        return EnrollmentResponse.builder()
                .id(enrollment.getId())
                .studentId(enrollment.getStudent().getId())
                .studentName(enrollment.getStudent().getUser().getFullName())
                .courseId(enrollment.getCourse().getId())
                .courseTitle(enrollment.getCourse().getTitle())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .build();
    }
}
