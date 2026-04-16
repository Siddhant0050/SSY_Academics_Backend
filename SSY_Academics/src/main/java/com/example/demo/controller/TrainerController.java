package com.example.demo.controller;

import com.example.demo.dto.TrainerDTO;
import com.example.demo.interfaces.TrainerService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    // 🔥 ADMIN ONLY
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<TrainerDTO> createTrainer(@RequestBody TrainerDTO dto) {
        return ResponseEntity.ok(trainerService.createTrainer(dto));
    }

    // 🔓 ADMIN + TRAINER
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TRAINER')")
    public ResponseEntity<List<TrainerDTO>> getAllTrainers() {
        return ResponseEntity.ok(trainerService.getAllTrainers());
    }

    // 🔓 ADMIN + TRAINER
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TRAINER')")
    public ResponseEntity<TrainerDTO> getTrainerById(@PathVariable Long id) {
        return ResponseEntity.ok(trainerService.getTrainerById(id));
    }

    // 🔥 ADMIN ONLY
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<TrainerDTO> updateTrainer(@PathVariable Long id,
                                                    @RequestBody TrainerDTO dto) {
        return ResponseEntity.ok(trainerService.updateTrainer(id, dto));
    }

    // 🔥 ADMIN ONLY
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
        return ResponseEntity.ok("Trainer deleted");
    }
}