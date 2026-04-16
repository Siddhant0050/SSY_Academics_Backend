package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CourseDTO;
import com.example.demo.interfaces.CourseService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/courses")
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	// 🔥 ADMIN ONLY → CREATE
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO dto) {
		return ResponseEntity.ok(courseService.createCourse(dto));
	}

	// 🔓 BOTH ADMIN & STUDENT → GET ALL
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','STUDENT')")
	public ResponseEntity<List<CourseDTO>> getAllCourses() {
		return ResponseEntity.ok(courseService.getAllCourses());
	}

	// 🔓 BOTH ADMIN & STUDENT → GET BY ID
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
	public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
		return ResponseEntity.ok(courseService.getCourseById(id));
	}

	// 🔥 ADMIN ONLY → UPDATE
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseDTO dto) {
		return ResponseEntity.ok(courseService.updateCourse(id, dto));
	}

	// 🔥 ADMIN ONLY → DELETE
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
		courseService.deleteCourse(id);
		return ResponseEntity.ok("Course deleted successfully");
	}
}