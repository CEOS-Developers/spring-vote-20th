package ceos.vote.domain.member.entity;

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

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private TeamType team;

    @Column(name = "vote_back")
    private Boolean voteBack;

    @Column(name = "vote_front")
    private Boolean voteFront;

    @Column(name = "vote_team")
    private Boolean voteTeam;

    @Builder
    public Member(String userId, String password, String email, String role, PartType part, String name, TeamType team, Boolean voteBack, Boolean voteFront, Boolean voteTeam) {

        this.userId = userId;
        this.password = password;
        this.email = email;
        this.role = role;
        this.part = part;
        this.name = name;
        this.team = team;
        this.voteBack = voteBack;
        this.voteFront = voteFront;
        this.voteTeam = voteTeam;
    }
}
