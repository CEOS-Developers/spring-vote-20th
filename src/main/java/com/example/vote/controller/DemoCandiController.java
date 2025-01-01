package com.example.vote.controller;

import com.example.vote.domain.Member;
import com.example.vote.dto.CandidateResponseDto;
import com.example.vote.dto.DemoCandidateResponseDto;
import com.example.vote.service.DemoCandiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class DemoCandiController {
    private final DemoCandiService demoCandiService;

    @GetMapping("")
    public ResponseEntity<List<DemoCandidateResponseDto>> getTeams() {
        final List<DemoCandidateResponseDto> responseDtos = demoCandiService.getTeams();
        return ResponseEntity.ok().body(responseDtos);
    }
}
