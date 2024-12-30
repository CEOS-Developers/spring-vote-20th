package photoground.ceos.vote.domain.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import photoground.ceos.vote.domain.vote.entity.LeaderVote;
import photoground.ceos.vote.domain.vote.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT v FROM LeaderVote v WHERE v.voter.id = :voterId")
    LeaderVote findByVoter_Id(Long voterId);
}
