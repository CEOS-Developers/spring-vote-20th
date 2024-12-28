package photoground.ceos.vote.domain.member.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Team {
    PHOTO_GROUND("포토그라운드"),
    ANGEL_BRIDGE("엔젤브릿지"),
    PEDAL_GENIE("페달지니"),
    CAKE_WAY("케이크WAY"),
    CUPFEE_DEAL("커피딜");

    private final String name;
}
