package ceos.vote.domain.vote.dto.response;

import ceos.vote.domain.developer.entity.Developer;
import lombok.Builder;

@Builder
public record DeveloperVoteResultResponseDto(
        String developerName,
        int count
) {
    public static DeveloperVoteResultResponseDto from(Developer developer) {
        return DeveloperVoteResultResponseDto.builder()
                .developerName(developer.getDeveloperName())
                .count(developer.getCount())
                .build();
    }
}
