package ceos.vote.domain.team.controller;

import ceos.vote.domain.team.dto.TeamRequestDto;
import ceos.vote.domain.team.service.TeamService;
import ceos.vote.global.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public CommonResponse<Void> createTeam(@RequestBody TeamRequestDto teamRequestDto) {

        teamService.createTeam(teamRequestDto);
        return new CommonResponse<>("팀 생성 성공");
    }
}
