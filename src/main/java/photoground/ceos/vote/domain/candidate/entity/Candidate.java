package photoground.ceos.vote.domain.candidate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import photoground.ceos.vote.domain.member.entity.Part;
import photoground.ceos.vote.domain.member.entity.Team;
import photoground.ceos.vote.global.entity.BaseTimeEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class Candidate extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Long id;

    private String name;

    @Builder.Default
    private Integer voteNum = 0;

    @Enumerated(EnumType.STRING)
    private Part part;

    @Enumerated(EnumType.STRING)
    private Team team;

    
    public void increaseVoteNum() {
        voteNum++;
    }

}
