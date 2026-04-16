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

    // ✅ CREATE
    @Override
    public CourseDTO createCourse(CourseDTO dto) {

        if (dto.getTitle() == null || dto.getTitle().isEmpty()) {
            throw new AppException("Course title is required", HttpStatus.BAD_REQUEST);
        }

        Course course = new Course();
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());

        return mapToDTO(courseRepo.save(course));
    }

    // ✅ GET ALL
    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ GET BY ID
    @Override
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new AppException(
                        "Course not found with ID: " + id,
                        HttpStatus.NOT_FOUND
                ));

        return mapToDTO(course);
    }

    // ✅ UPDATE
    @Override
    public CourseDTO updateCourse(Long id, CourseDTO dto) {

        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new AppException(
                        "Course not found with ID: " + id,
                        HttpStatus.NOT_FOUND
                ));

        if (dto.getTitle() != null && !dto.getTitle().isEmpty()) {
            course.setTitle(dto.getTitle());
        }

        course.setDescription(dto.getDescription());

        return mapToDTO(courseRepo.save(course));
    }

    // ✅ DELETE
    @Override
    public void deleteCourse(Long id) {

        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new AppException(
                        "Course not found with ID: " + id,
                        HttpStatus.NOT_FOUND
                ));

        courseRepo.delete(course);
    }

    // 🔁 MAPPER
    private CourseDTO mapToDTO(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getTitle(),
                course.getDescription()
        );
    }
}