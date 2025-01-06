package photoground.ceos.vote.domain.vote.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class LeaderResultListDTO {

    private List<LeaderResultDTO> leaderResults;


    public LeaderResultListDTO(List<LeaderResultDTO> leaderResults) {
        this.leaderResults = leaderResults;
    }

    public static LeaderResultListDTO from(List<LeaderResultDTO> leaderResults) {
        return new LeaderResultListDTO(leaderResults);
    }
}
