package com.example.vote.service;

import com.example.vote.domain.Member;
import com.example.vote.dto.LoginRequestDTO;
import com.example.vote.dto.LoginResponseDTO;
import com.example.vote.repository.MemberRepository;
import com.example.vote.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider; // JWT 생성 로직을 위한 클래스

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        // 1. 사용자 정보 확인
        Member member = memberRepository.findByUsername(loginRequestDTO.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        // 2. 비밀번호 비교
        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        // 3. JWT 토큰 생성
        String token = jwtTokenProvider.createToken(member.getUsername());

        // 디버깅: 토큰이 생성되지 않았다면 로그 출력
        if (token == null || token.isEmpty()) {
            throw new IllegalStateException("JWT Token generation failed");
        }

        // 4. 응답 반환
        return new LoginResponseDTO(member.getUsername(), token);
    }
}
