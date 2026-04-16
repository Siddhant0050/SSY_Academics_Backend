package com.example.demo.interfaces;

import com.example.demo.dto.TrainerDTO;
import java.util.List;

public interface TrainerService {

	TrainerDTO createTrainer(TrainerDTO dto);

	TrainerDTO getTrainerById(Long id);

	List<TrainerDTO> getAllTrainers();

	TrainerDTO updateTrainer(Long id, TrainerDTO dto);

	void deleteTrainer(Long id);
}