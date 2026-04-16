package com.example.demo.service;

import com.example.demo.Exception.AppException;
import com.example.demo.dto.TrainerDTO;
import com.example.demo.interfaces.TrainerService;
import com.example.demo.model.Trainer;
import com.example.demo.repository.TrainerRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepo;

    public TrainerServiceImpl(TrainerRepository trainerRepo) {
        this.trainerRepo = trainerRepo;
    }

    private TrainerDTO mapToDTO(Trainer trainer) {
        return new TrainerDTO(
                trainer.getId(),
                trainer.getName(),
                trainer.getEmail(),
                trainer.getExpertise()
        );
    }

    private Trainer mapToEntity(TrainerDTO dto) {
        Trainer trainer = new Trainer();
        trainer.setName(dto.getName());
        trainer.setEmail(dto.getEmail());
        trainer.setExpertise(dto.getExpertise());
        return trainer;
    }

    @Override
    public TrainerDTO createTrainer(TrainerDTO dto) {
        return mapToDTO(trainerRepo.save(mapToEntity(dto)));
    }

    @Override
    public TrainerDTO getTrainerById(Long id) {
        Trainer trainer = trainerRepo.findById(id)
                .orElseThrow(() -> new AppException("Trainer not found", HttpStatus.NOT_FOUND));
        return mapToDTO(trainer);
    }

    @Override
    public List<TrainerDTO> getAllTrainers() {
        return trainerRepo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TrainerDTO updateTrainer(Long id, TrainerDTO dto) {

        Trainer trainer = trainerRepo.findById(id)
                .orElseThrow(() -> new AppException("Trainer not found", HttpStatus.NOT_FOUND));

        trainer.setName(dto.getName());
        trainer.setEmail(dto.getEmail());
        trainer.setExpertise(dto.getExpertise());

        return mapToDTO(trainerRepo.save(trainer));
    }

    @Override
    public void deleteTrainer(Long id) {

        Trainer trainer = trainerRepo.findById(id)
                .orElseThrow(() -> new AppException("Trainer not found", HttpStatus.NOT_FOUND));

        trainerRepo.delete(trainer);
    }
}