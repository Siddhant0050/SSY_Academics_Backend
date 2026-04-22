package com.example.demo.service;

import com.example.demo.Exception.AppException;
import com.example.demo.dto.UserDTO;
import com.example.demo.interfaces.UserService;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE
    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setRole(Role.valueOf(userDTO.getRole()));

        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }

    // GET ALL
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // GET BY ID
    @Override
    public UserDTO getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new AppException("User not found with id: " + id, HttpStatus.NOT_FOUND));

        return mapToDTO(user);
    }

    // UPDATE
    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new AppException("User not found with id: " + id, HttpStatus.NOT_FOUND));

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setRole(Role.valueOf(userDTO.getRole()));

        User updated = userRepository.save(user);
        return mapToDTO(updated);
    }

    // DELETE
    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new AppException("User not found with id: " + id, HttpStatus.NOT_FOUND));

        userRepository.delete(user);
    }

    // MAPPER
    private UserDTO mapToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}