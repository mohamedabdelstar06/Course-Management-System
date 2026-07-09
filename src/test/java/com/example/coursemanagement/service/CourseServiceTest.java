package com.example.coursemanagement.service;

import com.example.coursemanagement.dto.CourseRequest;
import com.example.coursemanagement.dto.CourseResponse;
import com.example.coursemanagement.entity.Course;
import com.example.coursemanagement.entity.Instructor;
import com.example.coursemanagement.entity.User;
import com.example.coursemanagement.entity.UserRole;
import com.example.coursemanagement.exception.NotFoundException;
import com.example.coursemanagement.mapper.CourseMapper;
import com.example.coursemanagement.repository.CourseRepository;
import com.example.coursemanagement.repository.InstructorRepository;
import com.example.coursemanagement.service.impl.CourseServiceImpl;
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
 * Unit tests for CourseServiceImpl.
 * Repository layer is mocked — no Spring context, no database.
 */
@ExtendWith(MockitoExtension.class)
@SuppressWarnings("null")
@DisplayName("CourseService unit tests")
class CourseServiceTest {

    @Mock private CourseRepository courseRepository;
    @Mock private InstructorRepository instructorRepository;
    @Mock private CourseMapper courseMapper;

    @InjectMocks private CourseServiceImpl courseService;

    // -----------------------------------------------------------------------
    // Test fixtures
    // -----------------------------------------------------------------------

    private Instructor instructor;
    private Course course;
    private CourseRequest request;
    private CourseResponse response;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .id(1L)
                .fullName("Jane Instructor")
                .email("jane@example.com")
                .role(UserRole.INSTRUCTOR)
                .build();

        instructor = Instructor.builder().id(1L).user(user).build();

        course = Course.builder()
                .id(1L)
                .title("Spring Boot Basics")
                .description("Learn Spring Boot from scratch")
                .instructor(instructor)
                .createdAt(LocalDateTime.now())
                .build();   // deleted defaults to false via @Builder.Default

        request = new CourseRequest();
        request.setTitle("Spring Boot Basics");
        request.setDescription("Learn Spring Boot from scratch");
        request.setInstructorId(1L);

        response = CourseResponse.builder()
                .id(1L)
                .title("Spring Boot Basics")
                .description("Learn Spring Boot from scratch")
                .instructorId(1L)
                .instructorName("Jane Instructor")
                .createdAt(course.getCreatedAt())
                .build();
    }

    // -----------------------------------------------------------------------
    // create()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("create() — success path persists and returns response")
    void createCourse_success() {
        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));
        when(courseMapper.toEntity(request, instructor)).thenReturn(course);
        when(courseRepository.save(course)).thenReturn(course);
        when(courseMapper.toResponse(course)).thenReturn(response);

        CourseResponse result = courseService.create(request);

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Spring Boot Basics");
        assertThat(result.getInstructorId()).isEqualTo(1L);
        verify(courseRepository).save(course);
    }

    @Test
    @DisplayName("create() — instructor not found throws NotFoundException")
    void createCourse_instructorNotFound_throwsNotFoundException() {
        when(instructorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> courseService.create(request))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Instructor not found with id: 1");

        verify(courseRepository, never()).save(any());
    }

    // -----------------------------------------------------------------------
    // findById()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("findById() — existing non-deleted course returns response")
    void findCourseById_success() {
        when(courseRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(course));
        when(courseMapper.toResponse(course)).thenReturn(response);

        CourseResponse result = courseService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("findById() — not found or soft-deleted throws NotFoundException")
    void findCourseById_notFound_throwsNotFoundException() {
        when(courseRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> courseService.findById(1L))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Course not found with id: 1");
    }

    // -----------------------------------------------------------------------
    // softDelete()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("softDelete() — sets deleted=true and saves")
    void softDeleteCourse_success() {
        when(courseRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(course));
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        courseService.softDelete(1L);

        assertThat(course.isDeleted()).isTrue();
        verify(courseRepository).save(course);
    }

    @Test
    @DisplayName("softDelete() — not found throws NotFoundException")
    void softDeleteCourse_notFound_throwsNotFoundException() {
        when(courseRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> courseService.softDelete(1L))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Course not found with id: 1");

        verify(courseRepository, never()).save(any());
    }

    // -----------------------------------------------------------------------
    // update()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("update() — success path changes title and returns updated response")
    void updateCourse_success() {
        CourseRequest updateRequest = new CourseRequest();
        updateRequest.setTitle("Advanced Spring Boot");
        updateRequest.setDescription("Deep dive");
        updateRequest.setInstructorId(1L);

        CourseResponse updatedResponse = CourseResponse.builder()
                .id(1L).title("Advanced Spring Boot").build();

        when(courseRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(course));
        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));
        when(courseRepository.save(course)).thenReturn(course);
        when(courseMapper.toResponse(course)).thenReturn(updatedResponse);

        CourseResponse result = courseService.update(1L, updateRequest);

        assertThat(result.getTitle()).isEqualTo("Advanced Spring Boot");
        assertThat(course.getTitle()).isEqualTo("Advanced Spring Boot");
    }
}
