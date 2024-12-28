package photoground.ceos.vote.domain.vote.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import photoground.ceos.vote.domain.member.entity.Team;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DiscriminatorValue("T")
public class TeamVote extends Vote {

    private Team team;
}
