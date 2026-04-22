package com.example.demo.repository;

import com.example.demo.model.Batch;
import com.example.demo.model.Enrollment;
import com.example.demo.model.User;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    boolean existsByUserAndBatch(User user, Batch batch);

    long countByBatch(Batch batch);   // 🔥 MUST MATCH EXACTLY

    List<Enrollment> findByBatchId(Long batchId);
}