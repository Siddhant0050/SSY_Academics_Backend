package com.example.demo.dto;

public class BatchDTO {

	private Long id;
	private int capacity;
	private Long courseId;
	private Long trainerId; // ✅ NEW

	public BatchDTO() {
	}

	// ✅ FIXED CONSTRUCTOR
	public BatchDTO(Long id, int capacity, Long courseId, Long trainerId) {
		this.id = id;
		this.capacity = capacity;
		this.courseId = courseId;
		this.trainerId = trainerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(Long trainerId) {
		this.trainerId = trainerId;
	}

	// getters & setters
}