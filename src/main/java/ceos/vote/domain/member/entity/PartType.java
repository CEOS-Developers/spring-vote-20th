package ceos.vote.domain.member.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum PartType {

    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String description;

    PartType(String description) {
        this.description = description;
    }
}
