package com.example.demo.dto;

public class EnrollmentResponseDTO {

	private Long id;

	private Long userId;
	private String userName;

	private Long batchId;
	private String batchName;

	public EnrollmentResponseDTO() {
	}

	public EnrollmentResponseDTO(Long id, Long userId, String userName, Long batchId, String batchName) {
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.batchId = batchId;
		this.batchName = batchName;
	}

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public Long getBatchId() {
		return batchId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
}