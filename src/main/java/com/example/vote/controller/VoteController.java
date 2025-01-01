package com.example.vote.controller;

import com.example.vote.domain.Member;
import com.example.vote.dto.VoteResponseDto;
import com.example.vote.security.CustomUserDetails;
import com.example.vote.service.MemberService;
import com.example.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;
    private final MemberService memberService;

    // 후보자 투표
    @PostMapping("/{id}")
    public ResponseEntity<List<VoteResponseDto>> vote(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id) {
        final List<VoteResponseDto> responseDto = voteService.vote(userDetails, id);
        return ResponseEntity.ok().body(responseDto);
    }

    // 결과 조회
    @GetMapping("/{part}")
    public ResponseEntity getResult(@PathVariable Member.Part part) {
        final List<VoteResponseDto> resposneDto = voteService.getResult(part);
        return ResponseEntity.ok().body(resposneDto);
    }
}
