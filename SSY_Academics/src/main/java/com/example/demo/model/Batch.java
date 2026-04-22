package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "batches")
public class Batch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String batchName;

	private Integer capacity; // ✅ keep Integer

	private LocalDate startDate;
	private LocalDate endDate;

	private String status;

	@ManyToOne
	@JoinColumn(name = "course_id")
	@JsonIgnore
	private Course course;

	@ManyToOne
	@JoinColumn(name = "trainer_id")
	@JsonIgnore
	private Trainer trainer;

	// ✅ GETTERS

	public Long getId() {
		return id;
	}

	public String getBatchName() {
		return batchName;
	}

	public Integer getCapacity() {
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

	public Course getCourse() {
		return course;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	// ✅ SETTERS

	public void setId(Long id) {
		this.id = id;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public void setCapacity(Integer capacity) { // ✅ ONLY ONE SETTER
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

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}
}