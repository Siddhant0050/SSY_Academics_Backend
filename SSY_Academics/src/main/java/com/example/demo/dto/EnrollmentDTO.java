package com.example.demo.dto;

public class EnrollmentDTO {

	private Long id;
	private Long userId;
	private Long batchId;

	public EnrollmentDTO() {
	}

	public EnrollmentDTO(Long id, Long userId, Long batchId) {
		this.id = id;
		this.userId = userId;
		this.batchId = batchId;
	}

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public Long getUserId() {
		return userId;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

}