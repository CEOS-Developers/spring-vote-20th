package ceos.vote.domain.vote.dto.response;

import ceos.vote.domain.developer.entity.Developer;
import lombok.Builder;

@Builder
public record DeveloperVoteResultResponseDto(
        String developerName,
        String teamName,
        int count
) {
    public static DeveloperVoteResultResponseDto from(Developer developer) {
        return DeveloperVoteResultResponseDto.builder()
                .developerName(developer.getDeveloperName())
                .teamName(developer.getTeam().getTeamName())
                .count(developer.getCount())
                .build();
    }
}
