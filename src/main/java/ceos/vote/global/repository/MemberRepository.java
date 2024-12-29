package ceos.vote.global.repository;

import ceos.vote.domain.member.entity.Member;
import ceos.vote.domain.member.entity.PartType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String userId);

    Boolean existsMemberByUserId(String userId);

    List<Member> findByPart(PartType part);

    List<Member> findByPartOrderByCountDesc(PartType part);
}
