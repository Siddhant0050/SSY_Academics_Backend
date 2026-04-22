package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class CourseDTO {

	private Long id;

	@NotBlank(message = "Title is required")
	private String title;

	private String description;

	private Double price; // ✅ ADD THIS
	private String imageUrl; // ✅ ADD THIS

	// Constructors
	public CourseDTO() {
	}

	public CourseDTO(Long id, String title, String description, Double price, String imageUrl) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	// Getters & Setters

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	// existing getters/setters...
}