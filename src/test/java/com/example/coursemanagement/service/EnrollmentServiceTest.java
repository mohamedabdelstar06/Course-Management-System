package com.example.coursemanagement.service;

import com.example.coursemanagement.dto.EnrollmentRequest;
import com.example.coursemanagement.dto.EnrollmentResponse;
import com.example.coursemanagement.entity.Course;
import com.example.coursemanagement.entity.Enrollment;
import com.example.coursemanagement.entity.Instructor;
import com.example.coursemanagement.entity.Student;
import com.example.coursemanagement.entity.User;
import com.example.coursemanagement.entity.UserRole;
import com.example.coursemanagement.exception.ConflictException;
import com.example.coursemanagement.exception.NotFoundException;
import com.example.coursemanagement.mapper.EnrollmentMapper;
import com.example.coursemanagement.repository.CourseRepository;
import com.example.coursemanagement.repository.EnrollmentRepository;
import com.example.coursemanagement.repository.StudentRepository;
import com.example.coursemanagement.service.impl.EnrollmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for EnrollmentServiceImpl.
 * Repository layer is mocked — no Spring context, no database.
 */
@ExtendWith(MockitoExtension.class)
@SuppressWarnings("null")
@DisplayName("EnrollmentService unit tests")
class EnrollmentServiceTest {

    @Mock private EnrollmentRepository enrollmentRepository;
    @Mock private StudentRepository studentRepository;
    @Mock private CourseRepository courseRepository;
    @Mock private EnrollmentMapper enrollmentMapper;

    @InjectMocks private EnrollmentServiceImpl enrollmentService;

    // -----------------------------------------------------------------------
    // Test fixtures
    // -----------------------------------------------------------------------

    private Student student;
    private Course course;
    private Enrollment enrollment;
    private EnrollmentRequest request;
    private EnrollmentResponse response;

    @BeforeEach
    void setUp() {
        User studentUser = User.builder()
                .id(1L).fullName("Alice Student")
                .email("alice@example.com").role(UserRole.STUDENT).build();

        User instructorUser = User.builder()
                .id(2L).fullName("Bob Instructor")
                .email("bob@example.com").role(UserRole.INSTRUCTOR).build();

        student = Student.builder().id(1L).user(studentUser).build();
        Instructor instructor = Instructor.builder().id(1L).user(instructorUser).build();

        course = Course.builder()
                .id(1L).title("Java Basics")
                .instructor(instructor)
                .createdAt(LocalDateTime.now())
                .build();   // deleted = false via @Builder.Default

        enrollment = Enrollment.builder()
                .id(1L).student(student).course(course)
                .enrollmentDate(LocalDateTime.now()).build();

        request = new EnrollmentRequest();
        request.setStudentId(1L);
        request.setCourseId(1L);

        response = EnrollmentResponse.builder()
                .id(1L).studentId(1L).studentName("Alice Student")
                .courseId(1L).courseTitle("Java Basics").build();
    }

    // -----------------------------------------------------------------------
    // enroll()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("enroll() — success path creates enrollment and returns response")
    void enroll_success() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(course));
        when(enrollmentRepository.existsByStudentIdAndCourseId(1L, 1L)).thenReturn(false);
        when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(enrollment);
        when(enrollmentMapper.toResponse(enrollment)).thenReturn(response);

        EnrollmentResponse result = enrollmentService.enroll(request);

        assertThat(result).isNotNull();
        assertThat(result.getStudentId()).isEqualTo(1L);
        assertThat(result.getCourseId()).isEqualTo(1L);
        verify(enrollmentRepository).save(any(Enrollment.class));
    }

    @Test
    @DisplayName("enroll() — duplicate enrollment throws ConflictException")
    void enroll_duplicate_throwsConflictException() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(course));
        when(enrollmentRepository.existsByStudentIdAndCourseId(1L, 1L)).thenReturn(true);

        assertThatThrownBy(() -> enrollmentService.enroll(request))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("already enrolled");

        verify(enrollmentRepository, never()).save(any());
    }

    @Test
    @DisplayName("enroll() — student not found throws NotFoundException")
    void enroll_studentNotFound_throwsNotFoundException() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> enrollmentService.enroll(request))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Student not found with id: 1");

        verify(enrollmentRepository, never()).save(any());
    }

    @Test
    @DisplayName("enroll() — course not found throws NotFoundException")
    void enroll_courseNotFound_throwsNotFoundException() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> enrollmentService.enroll(request))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Course not found with id: 1");

        verify(enrollmentRepository, never()).save(any());
    }

    // -----------------------------------------------------------------------
    // unenroll()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("unenroll() — success path hard-deletes the enrollment")
    void unenroll_success() {
        when(enrollmentRepository.existsById(1L)).thenReturn(true);
        doNothing().when(enrollmentRepository).deleteById(1L);

        enrollmentService.unenroll(1L);

        verify(enrollmentRepository).deleteById(1L);
    }

    @Test
    @DisplayName("unenroll() — enrollment not found throws NotFoundException")
    void unenroll_notFound_throwsNotFoundException() {
        when(enrollmentRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> enrollmentService.unenroll(1L))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Enrollment not found with id: 1");

        verify(enrollmentRepository, never()).deleteById(any());
    }

    // -----------------------------------------------------------------------
    // getByStudent()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("getByStudent() — student not found throws NotFoundException")
    void getByStudent_studentNotFound_throwsNotFoundException() {
        when(studentRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> enrollmentService.getByStudent(99L))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Student not found with id: 99");
    }
}
