package ceos.vote.domain.vote.dto.response;

import ceos.vote.domain.member.entity.Member;
import ceos.vote.domain.team.entity.Team;
import lombok.Builder;

@Builder
public record TeamListResponseDto(
        Long teamId,
        String teamName,
        String description
) {

    public static TeamListResponseDto from (Team team) {
        return TeamListResponseDto.builder()
                .teamId(team.getId())
                .teamName(team.getTeamName())
                .description(team.getDescription())
                .build();
    }
}
