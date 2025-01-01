package com.example.vote.controller;

import com.example.vote.dto.LoginRequestDTO;
import com.example.vote.dto.LoginResponseDTO;
import com.example.vote.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO responseDTO = authService.login(loginRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
