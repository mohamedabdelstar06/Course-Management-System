package com.example.coursemanagement.service.impl;

import com.example.coursemanagement.dto.AuthResponse;
import com.example.coursemanagement.dto.LoginRequest;
import com.example.coursemanagement.dto.RegisterRequest;
import com.example.coursemanagement.entity.Instructor;
import com.example.coursemanagement.entity.Student;
import com.example.coursemanagement.entity.User;
import com.example.coursemanagement.entity.UserRole;
import com.example.coursemanagement.exception.ConflictException;
import com.example.coursemanagement.exception.NotFoundException;
import com.example.coursemanagement.exception.UnauthorizedException;
import com.example.coursemanagement.mapper.UserMapper;
import com.example.coursemanagement.repository.InstructorRepository;
import com.example.coursemanagement.repository.StudentRepository;
import com.example.coursemanagement.repository.UserRepository;
import com.example.coursemanagement.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.coursemanagement.config.JwtUtil;

import com.example.coursemanagement.service.FileStorageService;

/**
 * Handles registration and login.
 * Registration creates User + Student/Instructor atomically in one transaction.
 */
@Service
@Transactional(readOnly = true)
@SuppressWarnings("null")
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final FileStorageService fileStorageService;

    public AuthServiceImpl(UserRepository userRepository,
                           StudentRepository studentRepository,
                           InstructorRepository instructorRepository,
                           PasswordEncoder passwordEncoder,
                           UserMapper userMapper,
                           JwtUtil jwtUtil,
                           FileStorageService fileStorageService) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
        this.fileStorageService = fileStorageService;
    }

    // -----------------------------------------------------------------------
    // Register
    // -----------------------------------------------------------------------

    @Override
    @Transactional          // readOnly = false for writes
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("Email already in use: " + request.getEmail());
        }

        String profileImage = null;
        if (request.getImage() != null && !request.getImage().isEmpty()) {
            profileImage = fileStorageService.saveFile(request.getImage());
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .profileImage(profileImage)
                .build();
        user = userRepository.save(user);

        Long profileId;
        if (request.getRole() == UserRole.STUDENT) {
            Student student = studentRepository.save(Student.builder().user(user).build());
            profileId = student.getId();
        } else {
            Instructor instructor = instructorRepository.save(Instructor.builder().user(user).build());
            profileId = instructor.getId();
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return userMapper.toAuthResponse(user, profileId, token);
    }

    // -----------------------------------------------------------------------
    // Login
    // -----------------------------------------------------------------------

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("No account found for email: " + request.getEmail()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }

        Long profileId = resolveProfileId(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return userMapper.toAuthResponse(user, profileId, token);
    }

    // -----------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------

    private Long resolveProfileId(User user) {
        if (user.getRole() == UserRole.STUDENT) {
            return studentRepository.findByUserId(user.getId())
                    .map(Student::getId)
                    .orElse(null);
        } else {
            return instructorRepository.findByUserId(user.getId())
                    .map(Instructor::getId)
                    .orElse(null);
        }
    }
}
