package com.example.vote.service;

import com.example.vote.domain.Candidate;
import com.example.vote.domain.DemoCandi;
import com.example.vote.domain.Member;
import com.example.vote.dto.CandidateResponseDto;
import com.example.vote.dto.DemoCandidateResponseDto;
import com.example.vote.repository.CandidateRepository;
import com.example.vote.repository.DemoCandiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DemoCandiService {
    private final DemoCandiRepository demoCandiRepository;

    public List<DemoCandidateResponseDto> getTeams() {
        List<DemoCandi> candidates = demoCandiRepository.findAll();

        List<DemoCandidateResponseDto> demoCandidateResponseDtos = new ArrayList<>();

        for (DemoCandi candidate : candidates) {
            DemoCandidateResponseDto candidateResponseDto = DemoCandidateResponseDto.builder()
                    .team(candidate.getTeam())
                    .id(candidate.getId())
                    .build();

            demoCandidateResponseDtos.add(candidateResponseDto);
        }

        demoCandidateResponseDtos = demoCandidateResponseDtos.stream().sorted(Comparator.comparing(DemoCandidateResponseDto::getTeam)).collect(Collectors.toList());
        return demoCandidateResponseDtos;
    }
}
