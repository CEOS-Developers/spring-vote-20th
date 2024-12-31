package photoground.ceos.vote.domain.vote.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class TeamResultListDTO {

    private List<TeamResultDTO> teamResults;

    
    public TeamResultListDTO(List<TeamResultDTO> teamResults) {
        this.teamResults = teamResults;
    }

    public static TeamResultListDTO from(List<TeamResultDTO> teamResults) {
        return new TeamResultListDTO(teamResults);
    }
}
