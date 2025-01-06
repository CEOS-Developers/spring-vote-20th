package photoground.ceos.vote.domain.vote.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class LeaderListDTO {

    private List<LeaderDTO> leaders;

    
    public LeaderListDTO(List<LeaderDTO> leaders) {
        this.leaders = leaders;
    }

    public static LeaderListDTO from(List<LeaderDTO> leaders) {
        return new LeaderListDTO(leaders);
    }
}
