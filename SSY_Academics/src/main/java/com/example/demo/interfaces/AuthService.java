package com.example.demo.interfaces;

import com.example.demo.dto.*;

public interface AuthService {

    AuthResponseDTO login(AuthRequestDTO request);

    void register(AuthRequestDTO request);
}