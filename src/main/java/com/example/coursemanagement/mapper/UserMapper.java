package com.example.coursemanagement.mapper;

import com.example.coursemanagement.dto.AuthResponse;
import com.example.coursemanagement.entity.User;
import org.springframework.stereotype.Component;

/**
 * Maps User entity → AuthResponse DTO.
 * Password is intentionally never copied into the response.
 */
@Component
public class UserMapper {

    /**
     * Base overload — no JWT token (used for internal lookups).
     *
     * @param user      the persisted User
     * @param profileId Student.id or Instructor.id matching this user's role
     */
    public AuthResponse toAuthResponse(User user, Long profileId) {
        return toAuthResponse(user, profileId, null);
    }

    /**
     * Full overload — includes JWT token returned after login/register.
     *
     * @param user      the persisted User
     * @param profileId Student.id or Instructor.id matching this user's role
     * @param token     JWT bearer token (null if JWT phase not yet active)
     */
    public AuthResponse toAuthResponse(User user, Long profileId, String token) {
        return AuthResponse.builder()
                .userId(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .profileId(profileId)
                .token(token)
                .profileImage(user.getProfileImage())
                .build();
    }
}
