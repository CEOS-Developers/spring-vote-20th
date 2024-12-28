package ceos.vote.domain.vote.dto.response;

import ceos.vote.domain.member.entity.Member;
import lombok.Builder;

@Builder
public record DeveloperListResponseDto(
        Long memberId,
        String memberName,
        String teamName
) {

    public static DeveloperListResponseDto from (Member member) {
        return DeveloperListResponseDto.builder()
                .memberId(member.getId())
                .memberName(member.getName())
                .teamName(member.getTeam().getDescription())
                .build();
    }
}
