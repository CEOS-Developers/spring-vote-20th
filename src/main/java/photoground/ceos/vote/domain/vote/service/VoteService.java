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
import photoground.ceos.vote.domain.vote.dto.VoteLeaderDTO;
import photoground.ceos.vote.domain.vote.entity.LeaderVote;
import photoground.ceos.vote.domain.vote.repository.VoteRepository;
import photoground.ceos.vote.global.exception.CustomException;
import photoground.ceos.vote.global.exception.ErrorCode;

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

    // 파트장 투표
    @Transactional
    public void vote(VoteLeaderDTO voteDTO, Long voterId) {

        //중복 투표 체크
        if(duplicateLeaderVote(voterId)) {
            throw new CustomException(ErrorCode.LEADER_VOTE_ALREADY_EXIST);
        }

        Long candidateId=voteDTO.getCandidateId();

        //후보자 id 유효성 체크
        if(!memberExist(candidateId)){
            throw new CustomException(ErrorCode.CANDIDATE_NOT_EXIST);
        }

        Member candidate=memberService.findById(candidateId);
        Member voter=memberService.findById(voterId);

        //파트 체크
        if(!validPart(candidate, voter)){
            throw new CustomException(ErrorCode.INVALID_PART_VOTE);
        }

        LeaderVote vote=new LeaderVote(candidate, voter);
        voteRepository.save(vote);
    }

    //중복 투표 체크
    private boolean duplicateLeaderVote(Long voterId) {
        LeaderVote vote=voteRepository.findByVoter_Id(voterId);
        return vote != null;
    }

    //후보자 id 유효성 체크
    private boolean memberExist(Long candidateId) {
        return memberService.existsById(candidateId);
    }

    //파트 체크
    private boolean validPart(Member candidate, Member voter) {
        Part candidatePart=candidate.getPart();
        Part myPart=voter.getPart();

        return candidatePart == myPart;
    }

}

