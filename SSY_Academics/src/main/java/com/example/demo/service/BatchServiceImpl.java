package com.example.demo.service;

import com.example.demo.Exception.AppException;
import com.example.demo.dto.BatchDTO;
import com.example.demo.interfaces.BatchService;
import com.example.demo.model.Batch;
import com.example.demo.model.Course;
import com.example.demo.model.Trainer;
import com.example.demo.repository.BatchRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.TrainerRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;
    private final CourseRepository courseRepository;
    private final TrainerRepository trainerRepository;

    public BatchServiceImpl(BatchRepository batchRepository,
                            CourseRepository courseRepository,
                            TrainerRepository trainerRepository) {
        this.batchRepository = batchRepository;
        this.courseRepository = courseRepository;
        this.trainerRepository = trainerRepository;
    }

    // ✅ Entity → DTO
    private BatchDTO mapToDTO(Batch batch) {
        return new BatchDTO(
                batch.getId(),
                batch.getCapacity(),
                batch.getCourse() != null ? batch.getCourse().getId() : null,
                batch.getTrainer() != null ? batch.getTrainer().getId() : null
        );
    }

    // ✅ DTO → Entity
    private Batch mapToEntity(BatchDTO dto) {

        if (dto.getCapacity() <= 0) {
            throw new AppException("Capacity must be greater than 0", HttpStatus.BAD_REQUEST);
        }

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new AppException(
                        "Course not found with ID: " + dto.getCourseId(),
                        HttpStatus.NOT_FOUND
                ));

        Trainer trainer = null;
        if (dto.getTrainerId() != null) {
            trainer = trainerRepository.findById(dto.getTrainerId())
                    .orElseThrow(() -> new AppException(
                            "Trainer not found with ID: " + dto.getTrainerId(),
                            HttpStatus.NOT_FOUND
                    ));
        }

        Batch batch = new Batch();
        batch.setId(dto.getId());
        batch.setCapacity(dto.getCapacity());
        batch.setCourse(course);
        batch.setTrainer(trainer);

        return batch;
    }

    // ✅ CREATE
    @Override
    public BatchDTO createBatch(BatchDTO batchDTO) {
        Batch batch = mapToEntity(batchDTO);
        return mapToDTO(batchRepository.save(batch));
    }

    // ✅ GET BY ID
    @Override
    public BatchDTO getBatchById(Long id) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Batch not found with ID: " + id,
                        HttpStatus.NOT_FOUND
                ));

        return mapToDTO(batch);
    }

    // ✅ GET ALL
    @Override
    public List<BatchDTO> getAllBatches() {
        return batchRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ UPDATE
    @Override
    public BatchDTO updateBatch(Long id, BatchDTO batchDTO) {

        Batch existing = batchRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Batch not found with ID: " + id,
                        HttpStatus.NOT_FOUND
                ));

        if (batchDTO.getCapacity() <= 0) {
            throw new AppException("Capacity must be greater than 0", HttpStatus.BAD_REQUEST);
        }

        existing.setCapacity(batchDTO.getCapacity());

        Course course = courseRepository.findById(batchDTO.getCourseId())
                .orElseThrow(() -> new AppException(
                        "Course not found with ID: " + batchDTO.getCourseId(),
                        HttpStatus.NOT_FOUND
                ));

        existing.setCourse(course);

        if (batchDTO.getTrainerId() != null) {
            Trainer trainer = trainerRepository.findById(batchDTO.getTrainerId())
                    .orElseThrow(() -> new AppException(
                            "Trainer not found with ID: " + batchDTO.getTrainerId(),
                            HttpStatus.NOT_FOUND
                    ));

            existing.setTrainer(trainer);
        }

        return mapToDTO(batchRepository.save(existing));
    }

    // ✅ DELETE
    @Override
    public void deleteBatch(Long id) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Batch not found with ID: " + id,
                        HttpStatus.NOT_FOUND
                ));

        batchRepository.delete(batch);
    }
}