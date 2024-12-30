package photoground.ceos.vote.domain.vote.dto;

import lombok.Builder;
import lombok.Getter;
import photoground.ceos.vote.domain.member.entity.Member;

@Getter
@Builder
public class LeaderResultDTO {

    private String leaderName;
    private Long leaderId;
    private String teamName;
    private int voteNum;

    public static LeaderResultDTO from(Member member) {
        return LeaderResultDTO.builder()
                .leaderName(member.getName())
                .leaderId(member.getId())
                .teamName(member.getTeam().getName())
                .voteNum(member.getVoteNum())
                .build();
    }

}
