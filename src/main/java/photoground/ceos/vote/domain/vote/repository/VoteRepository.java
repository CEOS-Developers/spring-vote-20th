package photoground.ceos.vote.domain.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import photoground.ceos.vote.domain.vote.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
