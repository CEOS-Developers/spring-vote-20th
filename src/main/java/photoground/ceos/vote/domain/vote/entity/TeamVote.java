package photoground.ceos.vote.domain.vote.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import photoground.ceos.vote.domain.member.entity.Member;
import photoground.ceos.vote.domain.member.entity.Team;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DiscriminatorValue("T")
public class TeamVote extends Vote {

    private Team team;

    public TeamVote(Team team, Member voter) {
        this.team = team;
        this.voter = voter;
    }
}
