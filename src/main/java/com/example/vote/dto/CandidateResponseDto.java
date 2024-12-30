package com.example.vote.dto;

import com.example.vote.domain.Candidate;
import com.example.vote.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateResponseDto {
    private int id;
    private Candidate.Name name;
}
