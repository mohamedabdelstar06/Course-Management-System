package com.example.coursemanagement.serviceImpl;

import com.example.coursemanagement.dto.EnrollmentRequest;
import com.example.coursemanagement.dto.EnrollmentResponse;
import com.example.coursemanagement.entity.Course;
import com.example.coursemanagement.entity.Enrollment;
import com.example.coursemanagement.entity.Student;
import com.example.coursemanagement.exception.ConflictException;
import com.example.coursemanagement.exception.NotFoundException;
import com.example.coursemanagement.mapper.EnrollmentMapper;
import com.example.coursemanagement.repository.CourseRepository;
import com.example.coursemanagement.repository.EnrollmentRepository;
import com.example.coursemanagement.repository.StudentRepository;
import com.example.coursemanagement.service.EnrollmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

/**
 * Handles enrol / unenrol / list operations.
 * Duplicate enrolment is detected at the service level (409) before the
 * DB unique constraint can fire.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@SuppressWarnings("null")
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentMapper enrollmentMapper;

    // -----------------------------------------------------------------------
    // Enrol
    // -----------------------------------------------------------------------

    @Override
    @Transactional
    public EnrollmentResponse enroll(EnrollmentRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + request.getStudentId()));

        Course course = courseRepository.findByIdAndDeletedFalse(request.getCourseId())
                .orElseThrow(() -> new NotFoundException("Course not found with id: " + request.getCourseId()));

        if (enrollmentRepository.existsByStudentIdAndCourseId(request.getStudentId(), request.getCourseId())) {
            throw new ConflictException("Student is already enrolled in this course");
        }

        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        if (course.getRegistrationStartTime() != null && now.isBefore(course.getRegistrationStartTime())) {
            throw new com.example.coursemanagement.exception.RegistrationWindowException(
                    String.format("Registration for this course is only open between %s and %s.",
                            course.getRegistrationStartTime(), course.getRegistrationEndTime()));
        }
        if (course.getRegistrationEndTime() != null && now.isAfter(course.getRegistrationEndTime())) {
            throw new com.example.coursemanagement.exception.RegistrationWindowException(
                    String.format("Registration for this course is only open between %s and %s.",
                            course.getRegistrationStartTime(), course.getRegistrationEndTime()));
        }

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .build();

        return enrollmentMapper.toResponse(enrollmentRepository.save(enrollment));
    }

    // -----------------------------------------------------------------------
    // List by student
    // -----------------------------------------------------------------------

    @Override
    public List<EnrollmentResponse> getByStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new NotFoundException("Student not found with id: " + studentId);
        }
        return enrollmentRepository.findAllByStudentId(studentId)
                .stream()
                .map(enrollmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    // -----------------------------------------------------------------------
    // Unenrol (hard delete)
    // -----------------------------------------------------------------------

    @Override
    @Transactional
    public void unenroll(Long id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new NotFoundException("Enrollment not found with id: " + id);
        }
        enrollmentRepository.deleteById(id);
    }
}
