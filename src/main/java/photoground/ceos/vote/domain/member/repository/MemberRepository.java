package photoground.ceos.vote.domain.member.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import photoground.ceos.vote.domain.member.entity.Member;
import photoground.ceos.vote.domain.member.entity.Part;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByPart(Part part);

    boolean existsByUsername(String username);

    Member findByUsername(String username);
}
