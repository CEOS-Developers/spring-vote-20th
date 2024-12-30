package ceos.vote.global.repository;

import ceos.vote.domain.developer.entity.Developer;
import ceos.vote.domain.member.entity.PartType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    List<Developer> findByPartOrderByCountDesc(PartType part);
}
