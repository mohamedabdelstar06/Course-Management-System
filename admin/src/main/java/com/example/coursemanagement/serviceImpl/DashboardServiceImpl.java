package com.example.coursemanagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.coursemanagement.dto.CourseResponse;
import com.example.coursemanagement.mapper.CourseMapper;
import com.example.coursemanagement.repository.CourseRepository;
import com.example.coursemanagement.repository.EnrollmentRepository;
import com.example.coursemanagement.repository.InstructorRepository;
import com.example.coursemanagement.repository.StudentRepository;
import com.example.coursemanagement.service.DashboardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Map<String, Object> getDashboardMetrics() {
        long totalCourses = courseRepository.count();
        long totalStudents = studentRepository.count();
        long totalInstructors = instructorRepository.count();
        long totalEnrollments = enrollmentRepository.count();

        // Popular courses: sort by enrollment count (in-memory for simplicity as requested)
        List<CourseResponse> popularCourses = courseRepository.findAllByDeletedFalse()
                .stream()
                .sorted((c1, c2) -> Integer.compare(
                        enrollmentRepository.countByCourseId(c2.getId()),
                        enrollmentRepository.countByCourseId(c1.getId())
                ))
                .limit(3)
                .map(courseMapper::toResponse)
                .collect(Collectors.toList());

        Map<String, Object> metrics = new HashMap<>();
        metrics.put("totalCourses", totalCourses);
        metrics.put("totalStudents", totalStudents);
        metrics.put("totalInstructors", totalInstructors);
        metrics.put("totalEnrollments", totalEnrollments);
        metrics.put("popularCourses", popularCourses);

        return metrics;
    }
}
