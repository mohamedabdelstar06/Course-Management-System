package com.example.coursemanagement.mapper;

import com.example.coursemanagement.dto.CourseRequest;
import com.example.coursemanagement.dto.CourseResponse;
import com.example.coursemanagement.entity.Course;
import com.example.coursemanagement.entity.Instructor;
import org.springframework.stereotype.Component;

/** Maps between Course entity and Course DTOs. */
@Component
public class CourseMapper {

    /**
     * Builds a new Course entity from a request DTO plus a resolved Instructor.
     * The instructor lookup is the caller's responsibility (service layer).
     */
    public Course toEntity(CourseRequest request, Instructor instructor) {
        return Course.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .instructor(instructor)
                // deleted defaults to false via @Builder.Default
                .build();
    }

    /** Projects a Course entity (with its Instructor + User) into a response DTO. */
    public CourseResponse toResponse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .instructorId(course.getInstructor().getId())
                .instructorName(course.getInstructor().getUser().getFullName())
                .createdAt(course.getCreatedAt())
                .courseImage(course.getCourseImage())
                .build();
    }
}
