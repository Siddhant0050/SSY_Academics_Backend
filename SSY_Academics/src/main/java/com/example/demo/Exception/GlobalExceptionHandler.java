package com.example.demo.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AppException.class)
	public ResponseEntity<ErrorResponse> handleAppException(AppException ex) {

		ErrorResponse error = new ErrorResponse(ex.getMessage(), ex.getStatus().value(), LocalDateTime.now());

		return new ResponseEntity<>(error, ex.getStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {

		ErrorResponse error = new ErrorResponse(ex.getMessage(), 500, LocalDateTime.now());

		return ResponseEntity.internalServerError().body(error);
	}
}