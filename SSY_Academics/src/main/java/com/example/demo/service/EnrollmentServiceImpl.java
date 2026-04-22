package com.example.demo.service;

import com.example.demo.dto.EnrollmentRequestDTO;
import com.example.demo.dto.EnrollmentResponseDTO;
import com.example.demo.interfaces.EnrollmentService;
import com.example.demo.model.Batch;
import com.example.demo.model.Enrollment;
import com.example.demo.model.User;
import com.example.demo.repository.BatchRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BatchRepository batchRepository;

    // ✅ ENROLL USER
    @Override
    public EnrollmentResponseDTO enrollUser(EnrollmentRequestDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Batch batch = batchRepository.findById(dto.getBatchId())
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        // ❌ Duplicate check
        if (enrollmentRepository.existsByUserAndBatch(user, batch)) {
            throw new RuntimeException("Student already enrolled");
        }

        // 🔥 Capacity check
        long count = enrollmentRepository.countByBatch(batch);

        if (batch.getCapacity() == null || batch.getCapacity() <= 0) {
            throw new RuntimeException("Batch capacity not set properly");
        }

        if (count >= batch.getCapacity()) {
            throw new RuntimeException("Batch is full");
        }

        // ✅ Save
        Enrollment enrollment = new Enrollment(user, batch);
        Enrollment saved = enrollmentRepository.save(enrollment);

        return mapToDTO(saved);
    }

    // ✅ GET ALL
    @Override
    public List<EnrollmentResponseDTO> getAllEnrollments() {
        return enrollmentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ GET BY BATCH
    @Override
    public List<EnrollmentResponseDTO> getByBatch(Long batchId) {
        return enrollmentRepository.findByBatchId(batchId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ DELETE
    @Override
    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }

    // ✅ MAPPER
    private EnrollmentResponseDTO mapToDTO(Enrollment e) {
        return new EnrollmentResponseDTO(
                e.getId(),
                e.getUser().getId(),
                e.getUser().getName(),
                e.getBatch().getId(),
                e.getBatch().getBatchName()
        );
    }
}