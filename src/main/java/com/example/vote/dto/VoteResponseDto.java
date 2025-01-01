package com.example.vote.dto;

import com.example.vote.domain.Candidate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteResponseDto {
    private int voteCount;
    private Candidate.Name candidateName;
}
