package com.example.coursemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/** Request body for POST /api/courses and PUT /api/courses/{id}. */
@Data
public class CourseRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Instructor ID is required")
    private Long instructorId;

    private MultipartFile image;
}
