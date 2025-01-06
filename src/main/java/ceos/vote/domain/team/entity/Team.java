package ceos.vote.domain.team.entity;

import ceos.vote.domain.member.entity.TeamType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", nullable = false)
    private Long id;

    @Column(name = "team_name", nullable = false)
    private String teamName;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private TeamType type;

    private String description;

    private int count = 0;

    public void voteToMe() {
        this.count++;
    }

    @Builder
    public Team(String name, String type, String description) {

        TeamType team = null;

        if (type.equals("포토그라운드"))
            team = TeamType.PHOTOGROUND;
        else if (type.equals("엔젤브릿지"))
            team = TeamType.ANGELBRIDGE;
        else if (type.equals("페달지니"))
            team = TeamType.PEDALGENIE;
        else if (type.equals("케이크웨이"))
            team = TeamType.CAKEWAY;
        else if (type.equals("커피딜"))
            team = TeamType.COFFEEDEAL;

        this.teamName = name;
        this.type = team;
        this.description = description;
    }
}
