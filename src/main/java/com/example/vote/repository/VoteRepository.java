package com.example.vote.repository;

import com.example.vote.domain.Member;
import com.example.vote.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long>  {
    //List<Vote> findByPart(Member.Part part);
}
