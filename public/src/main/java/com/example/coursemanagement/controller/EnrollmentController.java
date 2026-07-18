package com.example.coursemanagement.controller;
import com.example.coursemanagement.dto.EnrollmentRequest;
import com.example.coursemanagement.dto.EnrollmentResponse;
import com.example.coursemanagement.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    /** POST /api/enrollments — 201 Created or 409 Conflict (duplicate) */
    @PostMapping
    public ResponseEntity<EnrollmentResponse> enroll(@Valid @RequestBody EnrollmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.enroll(request));
    }

    /** GET /api/enrollments/student/{studentId} — 200 OK list of enrollments */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentResponse>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getByStudent(studentId));
    }

    /** DELETE /api/enrollments/{id} — 200 OK with JSON message */
    @DeleteMapping("/{id}")
    public ResponseEntity<java.util.Map<String, String>> unenroll(@PathVariable Long id) {
        enrollmentService.unenroll(id);
        return ResponseEntity.ok(java.util.Map.of("message", "Enrollment deleted successfully"));
    }
}
