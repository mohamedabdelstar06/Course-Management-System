package com.example.coursemanagement.service.impl;

import com.example.coursemanagement.dto.StudentResponse;
import com.example.coursemanagement.exception.NotFoundException;
import com.example.coursemanagement.mapper.StudentMapper;
import com.example.coursemanagement.repository.StudentRepository;
import com.example.coursemanagement.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Read-only student queries.
 * Students are created via AuthService.register() — not here.
 * The class-level readOnly transaction keeps the Hibernate session open
 * while the mapper accesses lazy user associations.
 */
@Service
@Transactional(readOnly = true)
@SuppressWarnings("null")
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository,
                              StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentResponse findById(Long id) {
        return studentRepository.findById(id)
                .map(studentMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + id));
    }

    @Override
    public List<StudentResponse> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());
    }
}
