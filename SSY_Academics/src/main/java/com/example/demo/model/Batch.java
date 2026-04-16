package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "batches")
public class Batch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int capacity;

	@ManyToOne
	private Course course;

	// 🔥 ADD THIS
	@ManyToOne
	private Trainer trainer;

	// Getters
	public Long getId() {
		return id;
	}

	public int getCapacity() {
		return capacity;
	}

	public Course getCourse() {
		return course;
	}

	public Trainer getTrainer() {
		return trainer;
	} // ✅ REQUIRED

	// Setters
	public void setId(Long id) {
		this.id = id;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	} // ✅ REQUIRED
}