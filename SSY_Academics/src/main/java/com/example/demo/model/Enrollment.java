package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "enrollments")
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User user;

	@ManyToOne
	private Batch batch;

	// Constructors
	public Enrollment() {
	}

	public Enrollment(User user, Batch batch) {
		this.user = user;
		this.batch = batch;
	}

	// Getters
	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Batch getBatch() {
		return batch;
	}

	// Setters
	public void setId(Long id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}
}