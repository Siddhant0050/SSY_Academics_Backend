package com.example.demo.interfaces;

import com.example.demo.dto.BatchDTO;
import java.util.List;

public interface BatchService {

	BatchDTO createBatch(BatchDTO batchDTO);

	BatchDTO getBatchById(Long id);

	List<BatchDTO> getAllBatches();

	BatchDTO updateBatch(Long id, BatchDTO batchDTO);

	void deleteBatch(Long id);
}
