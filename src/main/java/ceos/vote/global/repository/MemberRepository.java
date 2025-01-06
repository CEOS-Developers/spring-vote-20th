package ceos.vote.global.repository;

import ceos.vote.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String userId);

    Boolean existsMemberByUserId(String userId);
}
