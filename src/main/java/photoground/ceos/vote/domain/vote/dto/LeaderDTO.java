package photoground.ceos.vote.domain.vote.dto;

import lombok.Builder;
import lombok.Getter;
import photoground.ceos.vote.domain.candidate.entity.Candidate;

@Getter
@Builder
public class LeaderDTO {

    private Long leaderId;
    private String leaderName;
    private String teamName;

    public static LeaderDTO from(Candidate candidate) {
        return LeaderDTO.builder()
                .leaderId(candidate.getId())
                .leaderName(candidate.getName())
                .teamName(candidate.getTeam().getName())
                .build();
    }
}
