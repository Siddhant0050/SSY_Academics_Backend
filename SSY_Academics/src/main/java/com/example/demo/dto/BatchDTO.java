package com.example.demo.dto;

import java.time.LocalDate;

public class BatchDTO {

	private Long id;
	private String batchName;
	private int capacity;
	private LocalDate startDate;
	private LocalDate endDate;
	private String status;
	private Long courseId;
	private Long trainerId;

	public BatchDTO() {
	}

	public BatchDTO(Long id, String batchName, int capacity, LocalDate startDate, LocalDate endDate, String status,
			Long courseId, Long trainerId) {
		this.id = id;
		this.batchName = batchName;
		this.capacity = capacity;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.courseId = courseId;
		this.trainerId = trainerId;
	}

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public String getBatchName() {
		return batchName;
	}

	public int getCapacity() {
		return capacity;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public String getStatus() {
		return status;
	}

	public Long getCourseId() {
		return courseId;
	}

	public Long getTrainerId() {
		return trainerId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public void setTrainerId(Long trainerId) {
		this.trainerId = trainerId;
	}
}