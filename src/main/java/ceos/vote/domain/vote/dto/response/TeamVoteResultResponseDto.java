package ceos.vote.domain.vote.dto.response;

import ceos.vote.domain.team.entity.Team;
import lombok.Builder;

@Builder
public record TeamVoteResultResponseDto(
        String teamName,
        String description,
        int count
) {
    public static TeamVoteResultResponseDto from (Team team) {
        return TeamVoteResultResponseDto.builder()
                .teamName(team.getTeamName())
                .description(team.getDescription())
                .count(team.getCount())
                .build();
    }
}
