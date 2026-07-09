package com.example.coursemanagement.service;

import com.example.coursemanagement.dto.InstructorResponse;

import java.util.List;

public interface InstructorService {

    InstructorResponse findById(Long id);

    List<InstructorResponse> findAll();
}
