package com.example.vote.service;

import com.example.vote.domain.Candidate;
import com.example.vote.domain.Member;
import com.example.vote.dto.CandidateResponseDto;
import com.example.vote.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;

    public List<CandidateResponseDto> getLeader(Member.Part part) {
        List<Candidate> candidates = candidateRepository.findByPart(part);

        List<CandidateResponseDto> candidateResponseDtos = new ArrayList<>();

        for (Candidate candidate : candidates) {
            CandidateResponseDto candidateResponseDto = CandidateResponseDto.builder()
                    .name(candidate.getName())
                    .id(candidate.getId())
                    .build();

            candidateResponseDtos.add(candidateResponseDto);
        }

        candidateResponseDtos = candidateResponseDtos.stream().sorted(Comparator.comparing(CandidateResponseDto::getName)).collect(Collectors.toList());
        return candidateResponseDtos;
    }
}
