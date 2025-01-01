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
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer voteCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Member.Part part;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Name name;

    public enum Name {
        이희원,
        최지원,
        지민재,
        김류원,
        송유선,
        강다혜,
        권혜인,
        이가빈,
        박지수,
        윤영준,
        나혜인,
        유지민,
        황서아,
        임가현,
        최서지,
        김연수,
        이채원,
        이한슬,
        남승현,
        문서영
    }

    public void increaseVoteCount() {
        voteCount++;
    }

}
