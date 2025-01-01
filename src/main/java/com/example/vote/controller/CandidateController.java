package com.example.vote.controller;

import com.example.vote.domain.Candidate;
import com.example.vote.domain.Member;
import com.example.vote.dto.CandidateResponseDto;
import com.example.vote.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/leader")
public class CandidateController {

    private final CandidateService candidateService;

    //part별 후보자 리스트 조회
    @GetMapping("/{part}")
    public ResponseEntity<List<CandidateResponseDto>> getLeader(@PathVariable Member.Part part) {
        final List<CandidateResponseDto> responseDtos = candidateService.getLeader(part);
        return ResponseEntity.ok().body(responseDtos);
    }
}
