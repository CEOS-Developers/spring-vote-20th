package ceos.vote.domain.team.dto;

import ceos.vote.domain.team.entity.Team;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record TeamRequestDto(
        String name,
        @Schema(description = "소속 팀명", example = "포토그라운드 | 엔젤브릿지 | 페달지니 | 케이크웨이 | 커피딜")
        @NotNull(message = "소속 팀명은 필수 입력 사항입니다.")
        String team,
        String description
) {

    public Team toEntity() {

        return Team.builder()
                .name(name)
                .type(team)
                .description(description)
                .build();
    }
}
