package com.example.coursemanagement.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    /**
     * Saves the uploaded file and returns the file path/name.
     * @param file the multipart file to save
     * @return the relative URL path to the saved file
     */
    String saveFile(MultipartFile file);
}
