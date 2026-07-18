package com.example.coursemanagement.mapper;

import com.example.coursemanagement.dto.StudentResponse;
import com.example.coursemanagement.entity.Student;
import org.springframework.stereotype.Component;

/** Maps Student entity → StudentResponse DTO. */
@Component
public class StudentMapper {

    public StudentResponse toResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .userId(student.getUser().getId())
                .fullName(student.getUser().getFullName())
                .email(student.getUser().getEmail())
                .profileImage(student.getUser().getProfileImage())
                .build();
    }
}
