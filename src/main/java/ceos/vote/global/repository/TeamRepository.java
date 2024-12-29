package ceos.vote.global.repository;

import ceos.vote.domain.member.entity.TeamType;
import ceos.vote.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findAllByOrderByCountDesc();

    Team findByType(TeamType type);
}
