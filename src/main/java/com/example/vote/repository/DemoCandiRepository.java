package com.example.vote.repository;

import com.example.vote.domain.Candidate;
import com.example.vote.domain.DemoCandi;
import com.example.vote.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemoCandiRepository extends JpaRepository<DemoCandi, Long> {
    List<DemoCandi> findByTeam(Member.Team team);
}
