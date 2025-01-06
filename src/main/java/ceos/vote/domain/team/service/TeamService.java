package ceos.vote.domain.team.service;

import ceos.vote.domain.team.dto.TeamRequestDto;
import ceos.vote.domain.team.entity.Team;
import ceos.vote.global.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public void createTeam(TeamRequestDto teamRequestDto) {

        Team team = teamRequestDto.toEntity();
        teamRepository.save(team);
    }
}
