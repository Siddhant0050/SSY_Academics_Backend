package com.example.demo.controller;

import com.example.demo.dto.EnrollmentRequestDTO;
import com.example.demo.dto.EnrollmentResponseDTO;
import com.example.demo.interfaces.EnrollmentService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // 🔥 ADMIN ONLY → Assign user to batch
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EnrollmentResponseDTO> enrollUser(@RequestBody EnrollmentRequestDTO dto) {
        return ResponseEntity.ok(enrollmentService.enrollUser(dto));
    }

    // 🔓 ADMIN & STUDENT → View all enrollments
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    public ResponseEntity<List<EnrollmentResponseDTO>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    // 🔓 GET BY BATCH (IMPORTANT)
    @GetMapping("/batch/{batchId}")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    public ResponseEntity<List<EnrollmentResponseDTO>> getByBatch(@PathVariable Long batchId) {
        return ResponseEntity.ok(enrollmentService.getByBatch(batchId));
    }

    // 🔥 ADMIN ONLY → Delete enrollment
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.ok("Enrollment deleted successfully");
    }
}