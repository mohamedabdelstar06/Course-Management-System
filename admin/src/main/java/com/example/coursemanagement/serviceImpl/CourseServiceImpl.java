package com.example.coursemanagement.serviceImpl;

import com.example.coursemanagement.dto.CourseRequest;
import com.example.coursemanagement.dto.CourseResponse;
import com.example.coursemanagement.entity.Course;
import com.example.coursemanagement.entity.Instructor;
import com.example.coursemanagement.exception.NotFoundException;
import com.example.coursemanagement.mapper.CourseMapper;
import com.example.coursemanagement.repository.CourseRepository;
import com.example.coursemanagement.repository.InstructorRepository;
import com.example.coursemanagement.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.coursemanagement.service.FileStorageService;

import lombok.RequiredArgsConstructor;

/**
 * Course CRUD + soft-delete logic.
 * All read methods run in a read-only transaction so that the Hibernate session
 * stays open while the mapper accesses lazy associations.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@SuppressWarnings("null")
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final CourseMapper courseMapper;
    private final FileStorageService fileStorageService;

    // -----------------------------------------------------------------------
    // Write operations
    // -----------------------------------------------------------------------

    @Override
    @Transactional
    public CourseResponse create(CourseRequest request) {
        Instructor instructor = resolveInstructor(request.getInstructorId());
        
        String courseImage = null;
        if (request.getImage() != null && !request.getImage().isEmpty()) {
            courseImage = fileStorageService.saveFile(request.getImage());
        }

        Course course = courseMapper.toEntity(request, instructor);
        course.setCourseImage(courseImage);

        Course saved = courseRepository.save(course);
        return courseMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public CourseResponse update(Long id, CourseRequest request) {
        Course course = resolveActiveCourse(id);
        Instructor instructor = resolveInstructor(request.getInstructorId());

        if (request.getImage() != null && !request.getImage().isEmpty()) {
            String courseImage = fileStorageService.saveFile(request.getImage());
            course.setCourseImage(courseImage);
        }

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setInstructor(instructor);

        return courseMapper.toResponse(courseRepository.save(course));
    }

    @Override
    @Transactional
    public void softDelete(Long id) {
        Course course = resolveActiveCourse(id);
        course.setDeleted(true);
        courseRepository.save(course);
    }

    @Override
    @Transactional
    public CourseResponse assignInstructor(Long courseId, Long instructorId) {
        Course course = resolveActiveCourse(courseId);
        Instructor instructor = resolveInstructor(instructorId);
        course.setInstructor(instructor);
        return courseMapper.toResponse(courseRepository.save(course));
    }

    // -----------------------------------------------------------------------
    // Read operations (readOnly transaction from class-level annotation)
    // -----------------------------------------------------------------------

    @Override
    public Page<CourseResponse> findAll(Pageable pageable) {
        return courseRepository.findAllByDeletedFalse(pageable)
                .map(courseMapper::toResponse);
    }

    @Override
    public CourseResponse findById(Long id) {
        return courseMapper.toResponse(resolveActiveCourse(id));
    }

    // -----------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------

    private Course resolveActiveCourse(Long id) {
        return courseRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Course not found with id: " + id));
    }

    private Instructor resolveInstructor(Long instructorId) {
        return instructorRepository.findById(instructorId)
                .orElseThrow(() -> new NotFoundException("Instructor not found with id: " + instructorId));
    }
}
