package com.example.coursemanagement.service;

import com.example.coursemanagement.entity.Course;
import com.example.coursemanagement.mapper.CourseMapper;
import com.example.coursemanagement.repository.CourseRepository;
import com.example.coursemanagement.repository.EnrollmentRepository;
import com.example.coursemanagement.repository.InstructorRepository;
import com.example.coursemanagement.repository.StudentRepository;
import com.example.coursemanagement.serviceImpl.DashboardServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DashboardServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private DashboardServiceImpl dashboardService;

    @Test
    void testGetDashboardMetrics() {
        when(courseRepository.count()).thenReturn(10L);
        when(studentRepository.count()).thenReturn(50L);
        when(instructorRepository.count()).thenReturn(5L);
        when(enrollmentRepository.count()).thenReturn(100L);

        when(courseRepository.findAllByDeletedFalse()).thenReturn(java.util.Collections.emptyList());

        Map<String, Object> metrics = dashboardService.getDashboardMetrics();

        assertEquals(10L, metrics.get("totalCourses"));
        assertEquals(50L, metrics.get("totalStudents"));
        assertEquals(5L, metrics.get("totalInstructors"));
        assertEquals(100L, metrics.get("totalEnrollments"));
    }
}
