package photoground.ceos.vote.domain.vote.dto;

import lombok.Builder;
import lombok.Getter;
import photoground.ceos.vote.domain.member.entity.Member;

@Getter
@Builder
public class LeaderDTO {

    private Long leaderId;
    private String leaderName;
    private String teamName;

    public static LeaderDTO from(Member member){
        return LeaderDTO.builder()
                .leaderId(member.getId())
                .leaderName(member.getName())
                .teamName(member.getTeam().name())
                .build();
    }
}
