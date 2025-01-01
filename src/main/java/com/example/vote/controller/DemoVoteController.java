package com.example.vote.controller;

import com.example.vote.domain.Member;
import com.example.vote.dto.DemoVoteResponseDto;
import com.example.vote.dto.VoteResponseDto;
import com.example.vote.security.CustomUserDetails;
import com.example.vote.service.DemoVoteService;
import com.example.vote.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/demoday")
public class DemoVoteController {

    private final DemoVoteService demoVoteService;
    private final MemberService memberService;

    // 데모데이 투표
    @PostMapping("/{id}")
    public ResponseEntity<List<DemoVoteResponseDto>> vote(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id) {
        final List<DemoVoteResponseDto> responseDto = demoVoteService.vote(userDetails, id);
        return ResponseEntity.ok().body(responseDto);
    }

    // 결과 조회
    @GetMapping("")
    public ResponseEntity getResult() {
        final List<DemoVoteResponseDto> resposneDto = demoVoteService.getResult();
        return ResponseEntity.ok().body(resposneDto);
    }
}
