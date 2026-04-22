package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String description;

	@Column(name = "price") // ✅ ensure mapping
	private Double price;

	@Column(name = "image_url") // ✅ IMPORTANT (DB uses snake_case)
	private String imageUrl;

	public Course() {
	}

	public Course(Long id, String title, String description, Double price, String imageUrl) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.imageUrl = imageUrl;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	// getters & setters
}