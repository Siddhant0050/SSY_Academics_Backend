package com.example.demo.controller;

import com.example.demo.dto.BatchDTO;
import com.example.demo.interfaces.BatchService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batches")
@CrossOrigin(origins = "*")
public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping
    public BatchDTO createBatch(@RequestBody BatchDTO dto) {
        return batchService.createBatch(dto);
    }

    @GetMapping
    public List<BatchDTO> getAll() {
        return batchService.getAllBatches();
    }

    @GetMapping("/{id}")
    public BatchDTO getById(@PathVariable Long id) {
        return batchService.getBatchById(id);
    }

    @PutMapping("/{id}")
    public BatchDTO update(@PathVariable Long id, @RequestBody BatchDTO dto) {
        return batchService.updateBatch(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        batchService.deleteBatch(id);
    }
}