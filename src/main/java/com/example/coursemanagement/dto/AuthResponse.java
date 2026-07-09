package com.example.coursemanagement.dto;

import com.example.coursemanagement.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Returned by register and login.
 * Contains no password.  {@code profileId} is the Student or Instructor PK
 * depending on the role.  {@code token} is null until the JWT bonus phase.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private Long userId;
    private String fullName;
    private String email;
    private UserRole role;

    /** Student.id or Instructor.id — whichever matches the user's role. */
    private Long profileId;

    /** JWT bearer token — populated only after the JWT bonus is enabled. */
    private String token;

    private String profileImage;
}
