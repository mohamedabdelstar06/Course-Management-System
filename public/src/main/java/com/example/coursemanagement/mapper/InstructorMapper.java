package com.example.coursemanagement.mapper;

import com.example.coursemanagement.dto.InstructorResponse;
import com.example.coursemanagement.entity.Instructor;
import org.springframework.stereotype.Component;

/** Maps Instructor entity → InstructorResponse DTO. */
@Component
public class InstructorMapper {

    public InstructorResponse toResponse(Instructor instructor) {
        return InstructorResponse.builder()
                .id(instructor.getId())
                .userId(instructor.getUser().getId())
                .fullName(instructor.getUser().getFullName())
                .email(instructor.getUser().getEmail())
                .profileImage(instructor.getUser().getProfileImage())
                .build();
    }
}
