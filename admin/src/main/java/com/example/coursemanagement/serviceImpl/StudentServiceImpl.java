package com.example.coursemanagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

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
 * Students are created via AuthService.register() â€” not here.
 * The class-level readOnly transaction keeps the Hibernate session open
 * while the mapper accesses lazy user associations.
 */
@Service
@Transactional(readOnly = true)
@SuppressWarnings("null")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;

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
