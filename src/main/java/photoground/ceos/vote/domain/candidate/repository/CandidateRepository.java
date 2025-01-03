package photoground.ceos.vote.domain.candidate.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import photoground.ceos.vote.domain.candidate.entity.Candidate;
import photoground.ceos.vote.domain.member.entity.Part;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findByPart(Part part);

}
