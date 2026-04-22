package com.example.demo.interfaces;

import com.example.demo.dto.EnrollmentRequestDTO;
import com.example.demo.dto.EnrollmentResponseDTO;

import java.util.List;

public interface EnrollmentService {

    // 🔥 Create enrollment
    EnrollmentResponseDTO enrollUser(EnrollmentRequestDTO dto);

    // 🔥 Get all enrollments
    List<EnrollmentResponseDTO> getAllEnrollments();

    // 🔥 Get enrollments by batch (VERY USEFUL for UI)
    List<EnrollmentResponseDTO> getByBatch(Long batchId);

    // 🔥 Delete
    void deleteEnrollment(Long id);
}