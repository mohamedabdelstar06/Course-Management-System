package com.example.coursemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.coursemanagement.dto.CourseRequest;
import com.example.coursemanagement.dto.CourseResponse;
import com.example.coursemanagement.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * GET /api/courses — 200 OK, paginated.
     * Supports ?page=0&size=10&sort=title,asc  (Spring MVC Pageable binding).
     * Soft-deleted courses are excluded.
     */
    @GetMapping
    public ResponseEntity<Page<CourseResponse>> findAll(
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.ok(courseService.findAll(pageable));
    }

    /** GET /api/courses/{id} — 200 OK or 404 */
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findById(id));
    }
}
