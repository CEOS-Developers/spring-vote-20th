package com.example.vote.controller;

import com.example.vote.dto.SignupRequestDTO;
import com.example.vote.dto.SignupResponseDTO;
import com.example.vote.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Authentication API", description = "APIs for user authentication")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    @Operation(summary = "Sign up a new user", description = "Register a new user with username, password, email, part, and team.")
    public ResponseEntity<SignupResponseDTO> signup(@RequestBody SignupRequestDTO signupRequestDTO) {
        SignupResponseDTO responseDTO = memberService.signup(signupRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
