package photoground.ceos.vote.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import photoground.ceos.vote.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Member findByUsername(String username);
}
