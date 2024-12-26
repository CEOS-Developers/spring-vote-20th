package ceos.vote.domain.member.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum TeamType {

    PHOTOGROUND("포토그라운드"),
    ANGELBRIDGE("엔젤브릿지"),
    PEDALGENIE("페달지니"),
    CAKEWAY("케이크웨이"),
    COFFEEDEAL("커피딜");

    private final String description;

    TeamType(String description) {
        this.description = description;
    }
}