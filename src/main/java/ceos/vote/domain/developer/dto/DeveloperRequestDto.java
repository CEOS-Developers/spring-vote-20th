package ceos.vote.domain.developer.dto;

import ceos.vote.domain.developer.entity.Developer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record DeveloperRequestDto(

        @Schema(description = "이름", example = "홍길동")
        @NotNull(message = "이름은 필수 입력 사항입니다.")
        String name,

        @Schema(description = "소속 팀명", example = "포토그라운드 | 엔젤브릿지 | 페달지니 | 케이크웨이 | 커피딜")
        @NotNull(message = "소속 팀명은 필수 입력 사항입니다.")
        String team,

        @Schema(description = "자기소개", example = "안녕하세요~ 이건 자기 소개입니다.")
        @NotNull(message = "자기소개는 필수 입력 사항입니다.")
        String introduction,

        @Schema(description = "소속 파트", example = "backend | frontend")
        @NotNull(message = "소속 파트는 필수 입력 사항입니다.")
        String part
) {
}
