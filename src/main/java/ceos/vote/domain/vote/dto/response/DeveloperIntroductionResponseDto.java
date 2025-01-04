package ceos.vote.domain.vote.dto.response;

import lombok.Builder;

@Builder
public record DeveloperIntroductionResponseDto(
        String name,
        String introduction
) {
}
