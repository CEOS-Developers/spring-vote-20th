package ceos.vote.domain.vote.dto.response;

import ceos.vote.domain.member.entity.Member;
import lombok.Builder;

@Builder
public record DeveloperVoteResultResponseDto(
        String memberName,
        int count
) {
    public static DeveloperVoteResultResponseDto from(Member m) {
        return DeveloperVoteResultResponseDto.builder()
                .memberName(m.getName())
                .count(m.getCount())
                .build();
    }
}
