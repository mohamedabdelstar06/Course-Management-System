package com.example.coursemanagement.controller;

import com.example.coursemanagement.dto.StudentResponse;
import com.example.coursemanagement.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Read-only student endpoints.
 * Students are created via POST /api/auth/register — not here.
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /** GET /api/students — 200 OK */
    @GetMapping
    public ResponseEntity<List<StudentResponse>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    /** GET /api/students/{id} — 200 OK or 404 */
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findById(id));
    }
}
