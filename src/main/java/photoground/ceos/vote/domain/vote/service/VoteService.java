package photoground.ceos.vote.domain.vote.service;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import photoground.ceos.vote.domain.member.entity.Member;
import photoground.ceos.vote.domain.member.entity.Part;
import photoground.ceos.vote.domain.member.entity.Team;
import photoground.ceos.vote.domain.member.service.MemberService;
import photoground.ceos.vote.domain.vote.dto.LeaderDTO;
import photoground.ceos.vote.domain.vote.dto.LeaderListDTO;
import photoground.ceos.vote.domain.vote.dto.TeamListDTO;
import photoground.ceos.vote.domain.vote.repository.VoteRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VoteService {

    private final VoteRepository voteRepository;
    private final MemberService memberService;

    //파트장 후보 조회
    public LeaderListDTO showLeaders(Part part){

        List<Member> members=memberService.findByPart(part);
        List<LeaderDTO> dtos=members.stream().map(LeaderDTO::from
        ).toList();
        return LeaderListDTO.from(dtos);
    }
    
    // 팀 후보 조회
    public TeamListDTO showTeams(){

        List<String> teamName= Arrays.stream(Team.values()).map(Team::getName).toList();
        return TeamListDTO.from(teamName);

    }

}
