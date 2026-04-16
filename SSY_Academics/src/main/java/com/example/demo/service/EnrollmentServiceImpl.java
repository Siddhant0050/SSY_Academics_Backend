package com.example.demo.service;

import com.example.demo.Exception.AppException;
import com.example.demo.dto.EnrollmentDTO;
import com.example.demo.interfaces.EnrollmentService;
import com.example.demo.model.Batch;
import com.example.demo.model.Enrollment;
import com.example.demo.model.User;
import com.example.demo.repository.BatchRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final BatchRepository batchRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,
                                 UserRepository userRepository,
                                 BatchRepository batchRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.batchRepository = batchRepository;
    }

    // DTO → Entity
    private Enrollment mapToEntity(User user, Batch batch) {
        return new Enrollment(user, batch);
    }

    // Entity → DTO
    private EnrollmentDTO mapToDTO(Enrollment enrollment) {
        return new EnrollmentDTO(
                enrollment.getId(),
                enrollment.getUser().getId(),
                enrollment.getBatch().getId()
        );
    }

    // ✅ CREATE ENROLLMENT (ADMIN already validated in controller)
    @Override
    public EnrollmentDTO enrollUser(EnrollmentDTO dto) {

        if (dto.getUserId() == null || dto.getBatchId() == null) {
            throw new AppException("userId and batchId must not be null", HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new AppException(
                        "User not found with ID: " + dto.getUserId(),
                        HttpStatus.NOT_FOUND
                ));

        Batch batch = batchRepository.findById(dto.getBatchId())
                .orElseThrow(() -> new AppException(
                        "Batch not found with ID: " + dto.getBatchId(),
                        HttpStatus.NOT_FOUND
                ));

        Enrollment enrollment = mapToEntity(user, batch);

        return mapToDTO(enrollmentRepository.save(enrollment));
    }

    @Override
    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEnrollment(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Enrollment not found with ID: " + id,
                        HttpStatus.NOT_FOUND
                ));

        enrollmentRepository.delete(enrollment);
    }
}