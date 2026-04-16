package com.example.demo.controller;

import com.example.demo.dto.BatchDTO;
import com.example.demo.interfaces.BatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/batches")
public class BatchController {

	private final BatchService batchService;

	public BatchController(BatchService batchService) {
		this.batchService = batchService;
	}

	// ✅ CREATE
	@PostMapping
	public ResponseEntity<BatchDTO> createBatch(@RequestBody BatchDTO batchDTO) {
		return ResponseEntity.ok(batchService.createBatch(batchDTO));
	}

	// ✅ GET BY ID
	@GetMapping("/{id}")
	public ResponseEntity<BatchDTO> getBatchById(@PathVariable Long id) {
		return ResponseEntity.ok(batchService.getBatchById(id));
	}

	// ✅ GET ALL
	@GetMapping
	public ResponseEntity<List<BatchDTO>> getAllBatches() {
		return ResponseEntity.ok(batchService.getAllBatches());
	}

	// ✅ UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<BatchDTO> updateBatch(@PathVariable Long id, @RequestBody BatchDTO batchDTO) {

		return ResponseEntity.ok(batchService.updateBatch(id, batchDTO));
	}

	// ✅ DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBatch(@PathVariable Long id) {
		batchService.deleteBatch(id);
		return ResponseEntity.ok("Batch deleted successfully");
	}
}