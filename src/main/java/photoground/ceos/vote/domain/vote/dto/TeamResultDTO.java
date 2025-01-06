package photoground.ceos.vote.domain.vote.dto;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import photoground.ceos.vote.domain.member.entity.Team;

@Getter
public class TeamResultDTO {

    private String teamName;
    private int voteNum;

    public TeamResultDTO(String teamName, int voteNum) {
        this.teamName = teamName;
        this.voteNum = voteNum;
    }

    public static List<TeamResultDTO> from(Map<Team, Integer> res) {
        return res.entrySet().stream()
                .map(entry -> new TeamResultDTO(entry.getKey().getName(), entry.getValue()))
                .sorted((dto1, dto2) -> Integer.compare(dto2.getVoteNum(), dto1.getVoteNum()))
                .toList();
    }
}
