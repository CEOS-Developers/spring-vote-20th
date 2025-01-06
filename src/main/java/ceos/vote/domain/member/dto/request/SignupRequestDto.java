package ceos.vote.domain.member.dto.request;

import ceos.vote.domain.member.entity.Member;
import ceos.vote.domain.member.entity.PartType;
import ceos.vote.domain.member.entity.TeamType;
import ceos.vote.domain.team.entity.Team;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record SignupRequestDto(

        @Schema(description = "이름", example = "홍길동")
        @NotNull(message = "이름은 필수 입력 사항입니다.")
        String name,

        @Schema(description = "아이디", example = "ceos2024")
        @NotNull(message = "아이디는 필수 입력 사항입니다.")
        String userId,

        @Schema(description = "비밀번호", example = "12345678")
        @NotNull(message = "비밀번호는 필수 입력 사항입니다.")
        String password,

        @Schema(description = "소속 팀명", example = "포토그라운드 | 엔젤브릿지 | 페달지니 | 케이크웨이 | 커피딜")
        @NotNull(message = "소속 팀명은 필수 입력 사항입니다.")
        String team,

        @Schema(description = "소속 파트", example = "프론트엔드 | 백엔드")
        @NotNull(message = "소속 파트는 필수 입력 사항입니다.")
        String part
) {
    public Member toEntity(String encodedPassword, PartType part, Team team) {
        return Member.builder()
                .userId(userId)
                .password(encodedPassword)
                .role("ROLE_USER")
                .part(part)
                .name(name)
                .team(team)
                .build();
    }
}

