package com.example.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.AppException;
import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.interfaces.AuthService;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepo;
	private final JwtUtil jwtUtil;
	private final PasswordEncoder passwordEncoder;

	public AuthServiceImpl(UserRepository userRepo, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.jwtUtil = jwtUtil;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public AuthResponseDTO login(AuthRequestDTO request) {

		User user = userRepo.findByEmail(request.getEmail())
				.orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new AppException("Invalid credentials", HttpStatus.UNAUTHORIZED);
		}

		String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

		return new AuthResponseDTO(token, user.getRole().name());
	}

	@Override
	public void register(AuthRequestDTO request) {

		if (userRepo.findByEmail(request.getEmail()).isPresent()) {
			throw new AppException("Email already exists", HttpStatus.CONFLICT);
		}

		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		// 🔥 ROLE LOGIC (FINAL)
		if (request.getRole() != null && request.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
			user.setRole(Role.ROLE_ADMIN);
		} else {
			user.setRole(Role.ROLE_STUDENT);
		}

		userRepo.save(user);
	}
}