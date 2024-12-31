package photoground.ceos.vote.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import photoground.ceos.vote.global.exception.CustomException;
import photoground.ceos.vote.global.exception.ErrorCode;

@AllArgsConstructor
@Getter
public enum Team {
    PHOTO_GROUND("포토그라운드"),
    ANGEL_BRIDGE("엔젤브릿지"),
    PEDAL_GENIE("페달지니"),
    CAKE_WAY("케이크WAY"),
    CUPFEE_DEAL("커피딜");

    private final String name;

    // 팀명 존재여부 체크
    public static Team validateTeamName(String teamName) {
        for (Team team : Team.values()) {
            if (team.getName().equals(teamName)) {
                return team;
            }
        }
        throw new CustomException(ErrorCode.NOT_FOUND_TEAM_NAME);
    }
}
