package com.example.demo.dto;

public class EnrollmentRequestDTO {

	private Long userId;
	private Long batchId;

	// --- GETTERS & SETTERS ---

	public Long getUserId() {
		return userId;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
}