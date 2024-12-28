package ceos.vote.domain.team.controller;

import ceos.vote.domain.team.dto.TeamRequestDto;
import ceos.vote.domain.team.entity.Team;
import ceos.vote.global.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamRepository teamRepository;

    @PostMapping
    public void createTeam(@RequestBody TeamRequestDto teamRequestDto) {

        Team team = teamRequestDto.toEntity();
        teamRepository.save(team);

    }
}
