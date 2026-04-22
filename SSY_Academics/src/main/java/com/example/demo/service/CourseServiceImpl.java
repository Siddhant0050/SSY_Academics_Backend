package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.AppException;
import com.example.demo.dto.CourseDTO;
import com.example.demo.model.Course;
import com.example.demo.interfaces.CourseService;
import com.example.demo.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepo;

	public CourseServiceImpl(CourseRepository courseRepo) {
		this.courseRepo = courseRepo;
	}

	// =========================
	// ✅ CREATE COURSE
	// =========================
	@Override
	public CourseDTO createCourse(CourseDTO dto) {

		if (dto.getTitle() == null || dto.getTitle().isEmpty()) {
			throw new AppException("Course title is required", HttpStatus.BAD_REQUEST);
		}

		Course course = mapToEntity(dto);
		Course savedCourse = courseRepo.save(course);

		return mapToDTO(savedCourse);
	}

	// =========================
	// ✅ GET ALL COURSES
	// =========================
	@Override
	public List<CourseDTO> getAllCourses() {
		return courseRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	// =========================
	// ✅ GET COURSE BY ID
	// =========================
	@Override
	public CourseDTO getCourseById(Long id) {
		Course course = courseRepo.findById(id)
				.orElseThrow(() -> new AppException("Course not found with ID: " + id, HttpStatus.NOT_FOUND));

		return mapToDTO(course);
	}

	// =========================
	// ✅ UPDATE COURSE
	// =========================
	@Override
	public CourseDTO updateCourse(Long id, CourseDTO dto) {

		Course course = courseRepo.findById(id)
				.orElseThrow(() -> new AppException("Course not found with ID: " + id, HttpStatus.NOT_FOUND));

		// Update only if provided
		if (dto.getTitle() != null && !dto.getTitle().isEmpty()) {
			course.setTitle(dto.getTitle());
		}

		if (dto.getDescription() != null) {
			course.setDescription(dto.getDescription());
		}

		if (dto.getPrice() != null) {
			course.setPrice(dto.getPrice());
		}

		if (dto.getImageUrl() != null) {
			course.setImageUrl(dto.getImageUrl());
		}

		Course updatedCourse = courseRepo.save(course);

		return mapToDTO(updatedCourse);
	}

	// =========================
	// ✅ DELETE COURSE
	// =========================
	@Override
	public void deleteCourse(Long id) {

		Course course = courseRepo.findById(id)
				.orElseThrow(() -> new AppException("Course not found with ID: " + id, HttpStatus.NOT_FOUND));

		courseRepo.delete(course);
	}

	// =========================
	// 🔁 ENTITY → DTO
	// =========================
	private CourseDTO mapToDTO(Course course) {
		CourseDTO dto = new CourseDTO();

		dto.setId(course.getId());
		dto.setTitle(course.getTitle());
		dto.setDescription(course.getDescription());

		dto.setPrice(course.getPrice()); // ✅ FIXED
		dto.setImageUrl(course.getImageUrl()); // ✅ FIXED

		return dto;
	}

	// =========================
	// 🔁 DTO → ENTITY
	// =========================
	private Course mapToEntity(CourseDTO dto) {
		Course course = new Course();

		course.setId(dto.getId());
		course.setTitle(dto.getTitle());
		course.setDescription(dto.getDescription());

		course.setPrice(dto.getPrice()); // ✅ FIXED
		course.setImageUrl(dto.getImageUrl()); // ✅ FIXED

		return course;
	}
}