package com.example.demo.interfaces;

import com.example.demo.dto.UserDTO;
import java.util.List;

public interface UserService {

    // CREATE
    UserDTO createUser(UserDTO userDTO);

    // READ - All Users
    List<UserDTO> getAllUsers();

    // READ - Single User
    UserDTO getUserById(Long id);

    // UPDATE
    UserDTO updateUser(Long id, UserDTO userDTO);

    // DELETE
    void deleteUser(Long id);
}