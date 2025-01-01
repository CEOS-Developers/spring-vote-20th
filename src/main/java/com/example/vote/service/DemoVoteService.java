package com.example.vote.service;

import com.example.vote.domain.*;
import com.example.vote.dto.DemoVoteResponseDto;
import com.example.vote.dto.VoteResponseDto;
import com.example.vote.repository.DemoCandiRepository;
import com.example.vote.repository.DemoVoteRepository;
import com.example.vote.repository.MemberRepository;
import com.example.vote.repository.VoteRepository;
import com.example.vote.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DemoVoteService {
    private final DemoVoteRepository demoVoteRepository;
    private final MemberRepository memberRepository;
    private final DemoCandiRepository demoCandiRepository;

    public List<DemoVoteResponseDto> getResult() {
        List<DemoCandi> demoCandies = demoCandiRepository.findAll();

        List<DemoVoteResponseDto> demoVoteResponseDtos = new ArrayList<DemoVoteResponseDto>();

        for (DemoCandi candi : demoCandies) {
            DemoVoteResponseDto voteResponseDto = DemoVoteResponseDto.builder()
                    .voteCount(candi.getVoteCount())
                    .team(candi.getTeam())
                    .build();

            demoVoteResponseDtos.add(voteResponseDto);
        }

        demoVoteResponseDtos = demoVoteResponseDtos.stream().sorted(Comparator.comparing(DemoVoteResponseDto::getVoteCount).reversed()).collect(Collectors.toList());

        return demoVoteResponseDtos;

    }

    public List<DemoVoteResponseDto> vote(CustomUserDetails userDetails, Long id){
        final Member member = memberRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("Member not found"));

        final DemoCandi candidate = demoCandiRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Candidate not found"));

        if (userDetails.getTeam() == candidate.getTeam()) {
            throw new IllegalArgumentException("Cannot vote same team");
        }

        // 투표 저장
        final DemoVote vote = DemoVote.builder()
                .member(member)
                .demoCandi(candidate).
                build();
        demoVoteRepository.save(vote);

        // 후보자 득표수 증가
        candidate.increaseVoteCount();
        demoCandiRepository.save(candidate);

        return getResult();
    }
}
