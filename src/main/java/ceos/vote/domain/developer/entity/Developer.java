package ceos.vote.domain.developer.entity;

import ceos.vote.domain.member.entity.PartType;
import ceos.vote.domain.team.entity.Team;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "developer_id", nullable = false)
    private Long id;

    private String developerName;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private PartType part;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    private int count = 0;

    public void voteToMe() {
        this.count++;
    }

    @Builder
    public Developer(String developerName, PartType part, Team team) {

        this.developerName = developerName;
        this.part = part;
        this.team = team;
    }

}
