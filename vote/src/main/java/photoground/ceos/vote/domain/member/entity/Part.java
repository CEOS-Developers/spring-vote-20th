package photoground.ceos.vote.domain.member.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Part {
    FRONTEND("프론트엔드"),
    BACKEND("백엔드");

    private final String name;
}
