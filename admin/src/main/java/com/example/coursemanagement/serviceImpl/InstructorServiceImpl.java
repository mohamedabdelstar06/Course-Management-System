package com.example.coursemanagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.coursemanagement.dto.InstructorResponse;
import com.example.coursemanagement.exception.NotFoundException;
import com.example.coursemanagement.mapper.InstructorMapper;
import com.example.coursemanagement.repository.InstructorRepository;
import com.example.coursemanagement.service.InstructorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;



//   Read-only instructor queries.
//   Instructors are created via AuthService.register() â€” not here.
//   The class level readOnly transaction keeps the Hibernate session open
//   while the mapper accesses lazy user associations.
 
@Service
@Transactional(readOnly = true)
@SuppressWarnings("null")
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private InstructorMapper instructorMapper;

    //
    @Override
    public InstructorResponse findById(Long id) {
        return instructorRepository.findById(id)
                .map(instructorMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Instructor not found with id: " + id));
    }

    @Override
    public List<InstructorResponse> findAll() {
        return instructorRepository.findAll()
                .stream()
                .map(instructorMapper::toResponse)
                .collect(Collectors.toList());
    }
}
