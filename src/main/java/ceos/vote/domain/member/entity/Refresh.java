package ceos.vote.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Refresh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_id", nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String refresh;

    @Column(nullable = false)
    private String expiration;

    @Builder
    public Refresh(String userId, String refresh, String expiration) {
        this.userId = userId;
        this.refresh = refresh;
        this.expiration = expiration;
    }
}
