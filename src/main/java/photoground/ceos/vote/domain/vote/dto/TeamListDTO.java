package photoground.ceos.vote.domain.vote.dto;

import java.util.List;
import lombok.Getter;
import photoground.ceos.vote.domain.member.entity.Team;

@Getter
public class TeamListDTO {

    private List<String> teams;

    public TeamListDTO(List<String> teams) {
        this.teams = teams;
    }

    public static TeamListDTO from(List<String> teams) {
        return new TeamListDTO(teams);
    }
}
