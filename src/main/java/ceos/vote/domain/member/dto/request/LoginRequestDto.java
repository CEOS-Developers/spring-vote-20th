package ceos.vote.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(

        @Schema(description = "아이디", example = "ceos2024")
        @NotNull
        String userId,

        @Schema(description = "비밀번호", example = "12345678")
        @NotNull
        String password
) {
}
