package com.example.vote.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "democandi")
public class DemoCandi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer voteCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Member.Team team;

    public void increaseVoteCount() {
        voteCount++;
    }
}
