package com.example.demo.service;

import com.example.demo.Exception.AppException;
import com.example.demo.dto.BatchDTO;
import com.example.demo.interfaces.BatchService;
import com.example.demo.model.*;
import com.example.demo.repository.*;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchServiceImpl implements BatchService {

	private final BatchRepository batchRepository;
	private final CourseRepository courseRepository;
	private final TrainerRepository trainerRepository;

	public BatchServiceImpl(BatchRepository batchRepository, CourseRepository courseRepository,
			TrainerRepository trainerRepository) {
		this.batchRepository = batchRepository;
		this.courseRepository = courseRepository;
		this.trainerRepository = trainerRepository;
	}

	// ✅ ENTITY → DTO
	private BatchDTO mapToDTO(Batch batch) {
		return new BatchDTO(batch.getId(), batch.getBatchName(), batch.getCapacity(), batch.getStartDate(),
				batch.getEndDate(), batch.getStatus(), batch.getCourse() != null ? batch.getCourse().getId() : null,
				batch.getTrainer() != null ? batch.getTrainer().getId() : null);
	}

	// ✅ DTO → ENTITY (FIXED)
	private Batch mapToEntity(BatchDTO dto) {

		if (dto.getCapacity() <= 0) {
			throw new AppException("Capacity must be > 0", HttpStatus.BAD_REQUEST);
		}

		Course course = courseRepository.findById(dto.getCourseId())
				.orElseThrow(() -> new AppException("Course not found", HttpStatus.NOT_FOUND));

		Trainer trainer = null;
		if (dto.getTrainerId() != null) {
			trainer = trainerRepository.findById(dto.getTrainerId())
					.orElseThrow(() -> new AppException("Trainer not found", HttpStatus.NOT_FOUND));
		}

		Batch batch = new Batch();
		batch.setId(dto.getId());
		batch.setBatchName(dto.getBatchName()); // 🔥 FIX
		batch.setCapacity(dto.getCapacity());
		batch.setStartDate(dto.getStartDate()); // 🔥 FIX
		batch.setEndDate(dto.getEndDate()); // 🔥 FIX
		batch.setStatus(dto.getStatus()); // 🔥 FIX
		batch.setCourse(course);
		batch.setTrainer(trainer);

		return batch;
	}

	@Override
	public BatchDTO createBatch(BatchDTO dto) {
		return mapToDTO(batchRepository.save(mapToEntity(dto)));
	}

	@Override
	public List<BatchDTO> getAllBatches() {
		return batchRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public BatchDTO getBatchById(Long id) {
		Batch batch = batchRepository.findById(id)
				.orElseThrow(() -> new AppException("Batch not found", HttpStatus.NOT_FOUND));
		return mapToDTO(batch);
	}

	@Override
	public BatchDTO updateBatch(Long id, BatchDTO dto) {

		Batch existing = batchRepository.findById(id)
				.orElseThrow(() -> new AppException("Batch not found", HttpStatus.NOT_FOUND));

		existing.setBatchName(dto.getBatchName());
		existing.setCapacity(dto.getCapacity());
		existing.setStartDate(dto.getStartDate());
		existing.setEndDate(dto.getEndDate());
		existing.setStatus(dto.getStatus());

		Course course = courseRepository.findById(dto.getCourseId())
				.orElseThrow(() -> new AppException("Course not found", HttpStatus.NOT_FOUND));

		existing.setCourse(course);

		if (dto.getTrainerId() != null) {
			Trainer trainer = trainerRepository.findById(dto.getTrainerId())
					.orElseThrow(() -> new AppException("Trainer not found", HttpStatus.NOT_FOUND));
			existing.setTrainer(trainer);
		}

		return mapToDTO(batchRepository.save(existing));
	}

	@Override
	public void deleteBatch(Long id) {
		Batch batch = batchRepository.findById(id)
				.orElseThrow(() -> new AppException("Batch not found", HttpStatus.NOT_FOUND));
		batchRepository.delete(batch);
	}
}