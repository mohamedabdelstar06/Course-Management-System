package com.example.coursemanagement.controller;

import com.example.coursemanagement.dto.InstructorResponse;
import com.example.coursemanagement.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Read-only instructor endpoints.
 * Instructors are created via POST /api/auth/register — not here.
 */
@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    /** GET /api/instructors — 200 OK */
    @GetMapping
    public ResponseEntity<List<InstructorResponse>> findAll() {
        return ResponseEntity.ok(instructorService.findAll());
    }

    /** GET /api/instructors/{id} — 200 OK or 404 */
    @GetMapping("/{id}")
    public ResponseEntity<InstructorResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(instructorService.findById(id));
    }
}
