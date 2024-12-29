package ceos.vote.domain.member.entity;

import ceos.vote.domain.team.entity.Team;
import ceos.vote.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;

    // 로그인 아이디
    @Column(name = "user_id", length = 50, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(length = 320, nullable = false)
    private String email;

    private String role;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private PartType part;

    @Column(length = 20, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "vote_back", nullable = false)
    private boolean voteBack = false;

    @Column(name = "vote_front", nullable = false)
    private boolean voteFront = false;

    @Column(name = "vote_team", nullable = false)
    private boolean voteTeam = false;

    private int count = 0;

    public void voteToMe() {
        this.count++;
    }

    public void voteToBack() {
        this.voteBack = true;
    }

    public void voteToFront() {
        this.voteFront = true;
    }

    public void voteToTeam() {
        this.voteTeam = true;
    }

    @Builder
    public Member(String userId, String password, String email, String role, PartType part, String name, Team team) {

        this.userId = userId;
        this.password = password;
        this.email = email;
        this.role = role;
        this.part = part;
        this.name = name;
        this.team = team;
    }
}
