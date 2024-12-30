package photoground.ceos.vote.domain.vote.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LeaderListDTO {

    private List<LeaderDTO> leaders;

    public static LeaderListDTO from(List<LeaderDTO> leaders) {
        return LeaderListDTO.builder()
                .leaders(leaders)
                .build();
    }
}
