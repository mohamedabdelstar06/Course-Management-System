package com.example.coursemanagement.service;

import com.example.coursemanagement.dto.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse findById(Long id);

    List<StudentResponse> findAll();
}
