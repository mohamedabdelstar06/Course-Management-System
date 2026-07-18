package com.example.coursemanagement.service;

import com.example.coursemanagement.dto.EnrollmentRequest;
import com.example.coursemanagement.entity.Course;
import com.example.coursemanagement.entity.Student;
import com.example.coursemanagement.exception.RegistrationWindowException;
import com.example.coursemanagement.mapper.EnrollmentMapper;
import com.example.coursemanagement.repository.CourseRepository;
import com.example.coursemanagement.repository.EnrollmentRepository;
import com.example.coursemanagement.repository.StudentRepository;
import com.example.coursemanagement.serviceImpl.EnrollmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceImplTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private EnrollmentMapper enrollmentMapper;

    @InjectMocks
    private EnrollmentServiceImpl enrollmentService;

    @Test
    void testEnroll_BeforeRegistrationStart_ThrowsException() {
        EnrollmentRequest request = new EnrollmentRequest();
        request.setStudentId(1L);
        request.setCourseId(1L);

        Student student = new Student();
        Course course = new Course();
        course.setRegistrationStartTime(LocalDateTime.now().plusDays(1)); // Future

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(course));

        assertThrows(RegistrationWindowException.class, () -> enrollmentService.enroll(request));
    }

    @Test
    void testEnroll_AfterRegistrationEnd_ThrowsException() {
        EnrollmentRequest request = new EnrollmentRequest();
        request.setStudentId(1L);
        request.setCourseId(1L);

        Student student = new Student();
        Course course = new Course();
        course.setRegistrationEndTime(LocalDateTime.now().minusDays(1)); // Past

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(course));

        assertThrows(RegistrationWindowException.class, () -> enrollmentService.enroll(request));
    }
}
