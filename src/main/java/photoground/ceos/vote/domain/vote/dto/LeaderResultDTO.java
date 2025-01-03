package photoground.ceos.vote.domain.vote.dto;

import lombok.Builder;
import lombok.Getter;
import photoground.ceos.vote.domain.candidate.entity.Candidate;

@Getter
@Builder
public class LeaderResultDTO {

    private String leaderName;
    private Long leaderId;
    private String teamName;
    private int voteNum;

    public static LeaderResultDTO from(Candidate candidate) {
        return LeaderResultDTO.builder()
                .leaderName(candidate.getName())
                .leaderId(candidate.getId())
                .teamName(candidate.getTeam().getName())
                .voteNum(candidate.getVoteNum())
                .build();
    }

}
