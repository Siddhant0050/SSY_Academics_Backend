package com.example.demo.interfaces;

import java.util.List;
import com.example.demo.dto.CourseDTO;

public interface CourseService {

    CourseDTO createCourse(CourseDTO dto);

    List<CourseDTO> getAllCourses();

    CourseDTO getCourseById(Long id);

    CourseDTO updateCourse(Long id, CourseDTO dto);

    void deleteCourse(Long id);
}