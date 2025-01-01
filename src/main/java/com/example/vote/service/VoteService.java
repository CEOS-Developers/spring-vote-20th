package com.example.vote.service;

import com.example.vote.domain.Candidate;
import com.example.vote.domain.Member;
import com.example.vote.domain.Vote;
import com.example.vote.dto.VoteResponseDto;
import com.example.vote.repository.CandidateRepository;
import com.example.vote.repository.MemberRepository;
import com.example.vote.repository.VoteRepository;
import com.example.vote.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final MemberRepository memberRepository;
    private final CandidateRepository candidateRepository;
    private final VoteRepository voteRepository;

    public List<VoteResponseDto> getResult(Member.Part part) {
        List<Candidate> candidates = candidateRepository.findByPart(part);

        List<VoteResponseDto> voteResponseDtos = new ArrayList<VoteResponseDto>();

        for (Candidate candidate : candidates) {
            VoteResponseDto voteResponseDto = VoteResponseDto.builder()
                    .voteCount(candidate.getVoteCount())
                    .candidateName(candidate.getName())
                    .build();

            voteResponseDtos.add(voteResponseDto);
        }

        voteResponseDtos = voteResponseDtos.stream().sorted(Comparator.comparing(VoteResponseDto::getVoteCount).reversed()).collect(Collectors.toList());

        return voteResponseDtos;

    }

    public List<VoteResponseDto> vote(CustomUserDetails userDetails, Long id){
        final Member member = memberRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("Member not found"));

        final Candidate candidate = candidateRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Candidate not found"));

        if (userDetails.getPart() != candidate.getPart()) {
            throw new IllegalArgumentException("Part not match");
        }

        // 투표 저장
        final Vote vote = Vote.builder()
                .member(member)
                .candidate(candidate).
                build();
        voteRepository.save(vote);

        // 후보자 득표수 증가
        candidate.increaseVoteCount();
        candidateRepository.save(candidate);

        return getResult(member.getPart());
    }
}
