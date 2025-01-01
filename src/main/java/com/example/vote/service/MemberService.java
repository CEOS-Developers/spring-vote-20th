package com.example.vote.service;

import com.example.vote.domain.Member;
import com.example.vote.dto.SignupRequestDTO;
import com.example.vote.dto.SignupResponseDTO;
import com.example.vote.dto.UserInfoDto;
import com.example.vote.repository.MemberRepository;
import com.example.vote.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder; // 주입받기

    public SignupResponseDTO signup(SignupRequestDTO signupRequestDTO) {
        // 중복 체크
        if (memberRepository.findByUsername(signupRequestDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists.");
        }
        if (memberRepository.findByEmail(signupRequestDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists.");
        }

        // 비밀번호 해싱
        String encodedPassword = passwordEncoder.encode(signupRequestDTO.getPassword());

        // Member 엔티티 생성 및 저장
        Member member = Member.builder()
                .username(signupRequestDTO.getUsername())
                .password(encodedPassword)
                .email(signupRequestDTO.getEmail())
                .name(signupRequestDTO.getName())
                .part(signupRequestDTO.getPart())
                .team(signupRequestDTO.getTeam())
                .build();

        memberRepository.save(member);

        return SignupResponseDTO.builder()
                .message("Signup successful!")
                .build();
    }

    public UserInfoDto getUserInfo(CustomUserDetails userDetails) {
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .username(userDetails.getUsername())
                .part(userDetails.getPart())
                .team(userDetails.getTeam())
                .build();

        return userInfoDto;
    }
}
