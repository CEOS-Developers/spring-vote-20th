package ceos.vote.domain.developer.service;

import ceos.vote.domain.developer.dto.DeveloperRequestDto;
import ceos.vote.domain.developer.entity.Developer;
import ceos.vote.domain.member.entity.PartType;
import ceos.vote.domain.member.entity.TeamType;
import ceos.vote.domain.team.entity.Team;
import ceos.vote.global.repository.DeveloperRepository;
import ceos.vote.global.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeveloperService {

    private final TeamRepository teamRepository;
    private final DeveloperRepository developerRepository;

    @Transactional
    public void createDeveloper(DeveloperRequestDto developerRequestDto) {

        // team
        TeamType type = null;
        if (developerRequestDto.team().equals("포토그라운드"))
            type = TeamType.PHOTOGROUND;
        else if (developerRequestDto.team().equals("엔젤브릿지"))
            type = TeamType.ANGELBRIDGE;
        else if (developerRequestDto.team().equals("페달지니"))
            type = TeamType.PEDALGENIE;
        else if (developerRequestDto.team().equals("케이크웨이"))
            type = TeamType.CAKEWAY;
        else if (developerRequestDto.team().equals("커피딜"))
            type = TeamType.COFFEEDEAL;
        Team team = teamRepository.findByType(type);

        // 파트 (백엔드 or 프론트)
        PartType partType = PartType.valueOf(developerRequestDto.part().toUpperCase());

        Developer developer = Developer.builder()
                .developerName(developerRequestDto.name())
                .part(partType)
                .team(team)
                .build();
        developerRepository.save(developer);
    }
}
