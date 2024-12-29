package ceos.vote.domain.member.dto.response;

import ceos.vote.domain.member.entity.Member;
import lombok.Builder;

@Builder
public record MemberResponseDto (

        String userId,
        String name,
        String email,
        String team,
        String part,
        Boolean voteBack,
        Boolean voteFront,
        Boolean voteTeam
) {
    public static MemberResponseDto from(Member member) {
        return MemberResponseDto.builder()
                .userId(member.getUserId())
                .name(member.getName())
                .email(member.getEmail())
                .team(member.getTeam().getDescription())
                .part(member.getPart().getDescription())
                .voteBack(member.isVoteBack())
                .voteFront(member.isVoteFront())
                .voteTeam(member.isVoteTeam())
                .build();
    }
}
