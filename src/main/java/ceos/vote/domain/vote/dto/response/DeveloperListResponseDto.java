package ceos.vote.domain.vote.dto.response;

import ceos.vote.domain.developer.entity.Developer;
import lombok.Builder;

@Builder
public record DeveloperListResponseDto(
        Long developerId,
        String developerName,
        String teamName
) {

    public static DeveloperListResponseDto from (Developer developer) {
        return DeveloperListResponseDto.builder()
                .developerId(developer.getId())
                .developerName(developer.getDeveloperName())
                .teamName(developer.getTeam().getDescription())
                .build();
    }
}
