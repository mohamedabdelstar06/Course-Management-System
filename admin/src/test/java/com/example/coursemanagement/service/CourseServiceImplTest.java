package com.example.coursemanagement.service;

import com.example.coursemanagement.dto.CourseResponse;
import com.example.coursemanagement.entity.Course;
import com.example.coursemanagement.entity.Instructor;
import com.example.coursemanagement.mapper.CourseMapper;
import com.example.coursemanagement.repository.CourseRepository;
import com.example.coursemanagement.repository.InstructorRepository;
import com.example.coursemanagement.serviceImpl.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.coursemanagement.service.FileStorageService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private CourseMapper courseMapper;
    
    @Mock
    private FileStorageService fileStorageService;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void testAssignInstructor() {
        Long courseId = 1L;
        Long instructorId = 2L;

        Course course = new Course();
        course.setId(courseId);

        Instructor instructor = new Instructor();
        instructor.setId(instructorId);

        when(courseRepository.findByIdAndDeletedFalse(courseId)).thenReturn(Optional.of(course));
        when(instructorRepository.findById(instructorId)).thenReturn(Optional.of(instructor));
        when(courseRepository.save(any(Course.class))).thenReturn(course);
        
        CourseResponse expectedResponse = new CourseResponse();
        when(courseMapper.toResponse(course)).thenReturn(expectedResponse);

        CourseResponse result = courseService.assignInstructor(courseId, instructorId);

        assertEquals(expectedResponse, result);
        verify(courseRepository).save(course);
        assertEquals(instructor, course.getInstructor());
    }
}
