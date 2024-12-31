package photoground.ceos.vote.domain.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import photoground.ceos.vote.domain.vote.entity.LeaderVote;
import photoground.ceos.vote.domain.vote.entity.TeamVote;
import photoground.ceos.vote.domain.vote.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT v FROM LeaderVote v WHERE v.voter.id = :voterId")
    LeaderVote findLeaderVoteByVoter_Id(Long voterId);

    @Query("SELECT v FROM TeamVote v WHERE v.voter.id= :voterId")
    TeamVote findTeamVoteByVoter_Id(Long voterId);
}
