package com.example.demo.dto;

public class TrainerDTO {

	private Long id;
	private String name;
	private String email;
	private String expertise;

	public TrainerDTO() {
	}

	public TrainerDTO(Long id, String name, String email, String expertise) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.expertise = expertise;
	}

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
}