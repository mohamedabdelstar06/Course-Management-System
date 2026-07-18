package com.example.coursemanagement.dto;

import com.example.coursemanagement.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private Long userId;
    private String fullName;
    private String email;
    private UserRole role;

    // Student.id or Instructor.id — whichever matches the user's role. */
    private Long profileId;

    // JWT bearer token — populated only after the JWT bonus is enabled. */
    private String token;

    private String profileImage;
}
