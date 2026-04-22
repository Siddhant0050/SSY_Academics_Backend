package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "enrollments", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "batch_id" }) // ✅
																												// prevent
																												// duplicate
																												// enrollment
})
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// ✅ USER RELATION
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private User user;

	// ✅ BATCH RELATION
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_id", nullable = false)
	@JsonIgnore
	private Batch batch;

	// ✅ BUSINESS FIELDS
	@Column(nullable = false)
	private String status; // ENROLLED / COMPLETED / DROPPED

	private Double feesPaid;

	private String paymentStatus; // PAID / PENDING

	private LocalDateTime enrolledAt;

	// --- CONSTRUCTORS ---

	public Enrollment() {
	}

	public Enrollment(User user, Batch batch) {
		this.user = user;
		this.batch = batch;
		this.status = "ENROLLED";
		this.paymentStatus = "PENDING";
		this.feesPaid = 0.0;
		this.enrolledAt = LocalDateTime.now();
	}

	// --- GETTERS & SETTERS ---

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Batch getBatch() {
		return batch;
	}

	public String getStatus() {
		return status;
	}

	public Double getFeesPaid() {
		return feesPaid;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public LocalDateTime getEnrolledAt() {
		return enrolledAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setFeesPaid(Double feesPaid) {
		this.feesPaid = feesPaid;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public void setEnrolledAt(LocalDateTime enrolledAt) {
		this.enrolledAt = enrolledAt;
	}
}