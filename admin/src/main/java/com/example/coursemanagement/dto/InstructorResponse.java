package com.example.coursemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Response body for instructor endpoints. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstructorResponse {

    private Long id;
    private Long userId;
    private String fullName;
    private String email;
    private String profileImage;
}
