package ceos.vote.domain.member.dto.request;

import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(

        @NotNull
        String userId,
        @NotNull
        String password
) {
}
