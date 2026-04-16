package com.example.demo.interfaces;

import com.example.demo.dto.EnrollmentDTO;
import java.util.List;

public interface EnrollmentService {

	EnrollmentDTO enrollUser(EnrollmentDTO dto);

	List<EnrollmentDTO> getAllEnrollments();

	void deleteEnrollment(Long id);
}