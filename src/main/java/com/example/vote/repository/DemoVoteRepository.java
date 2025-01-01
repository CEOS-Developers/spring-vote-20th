package com.example.vote.repository;

import com.example.vote.domain.DemoCandi;
import com.example.vote.domain.DemoVote;
import com.example.vote.domain.Member;
import com.example.vote.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemoVoteRepository extends JpaRepository<DemoVote, Long> {
    List<DemoVote> findByDemoCandi(DemoCandi demoCandi);
}
