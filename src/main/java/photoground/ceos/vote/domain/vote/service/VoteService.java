package photoground.ceos.vote.domain.vote.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import photoground.ceos.vote.domain.member.entity.Member;
import photoground.ceos.vote.domain.member.entity.Part;
import photoground.ceos.vote.domain.member.entity.Team;
import photoground.ceos.vote.domain.member.service.MemberService;
import photoground.ceos.vote.domain.vote.dto.LeaderDTO;
import photoground.ceos.vote.domain.vote.dto.LeaderListDTO;
import photoground.ceos.vote.domain.vote.dto.LeaderResultDTO;
import photoground.ceos.vote.domain.vote.dto.LeaderResultListDTO;
import photoground.ceos.vote.domain.vote.dto.TeamListDTO;
import photoground.ceos.vote.domain.vote.dto.TeamResultDTO;
import photoground.ceos.vote.domain.vote.dto.TeamResultListDTO;
import photoground.ceos.vote.domain.vote.dto.VoteLeaderDTO;
import photoground.ceos.vote.domain.vote.dto.VoteTeamDTO;
import photoground.ceos.vote.domain.vote.entity.LeaderVote;
import photoground.ceos.vote.domain.vote.entity.TeamVote;
import photoground.ceos.vote.domain.vote.repository.VoteRepository;
import photoground.ceos.vote.global.exception.CustomException;
import photoground.ceos.vote.global.exception.ErrorCode;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VoteService {

    private final VoteRepository voteRepository;
    private final MemberService memberService;

    // 파트장 후보 조회
    public LeaderListDTO showLeaders(Part part) {

        List<Member> members = memberService.findByPart(part);
        List<LeaderDTO> dtos = members.stream().map(LeaderDTO::from
        ).toList();
        return LeaderListDTO.from(dtos);
    }


    // 팀 후보 조회
    public TeamListDTO showTeams() {

        List<String> teamName = Arrays.stream(Team.values()).map(Team::getName).toList();
        return TeamListDTO.from(teamName);

    }


    // 파트장 투표
    @Transactional
    public void voteLeader(VoteLeaderDTO voteDTO, Long voterId) {

        //중복 투표 체크
        if (duplicateLeaderVote(voterId)) {
            throw new CustomException(ErrorCode.LEADER_VOTE_ALREADY_EXIST);
        }

        Long leaderId = voteDTO.getLeaderId();

        //후보자 id 유효성 체크
        if (!memberExist(leaderId)) {
            throw new CustomException(ErrorCode.LEADER_NOT_EXIST);
        }

        Member leader = memberService.findById(leaderId);
        Member voter = memberService.findById(voterId);

        //파트 체크
        if (!validPart(leader, voter)) {
            throw new CustomException(ErrorCode.INVALID_PART_VOTE);
        }

        LeaderVote vote = new LeaderVote(leader, voter);
        voteRepository.save(vote);
        leader.increaseVoteNum();
    }


    // 팀 투표
    @Transactional
    public void voteTeam(VoteTeamDTO voteDTO, Long voterId) {

        //중복 투표 체크
        if (duplicateTeamVote(voterId)) {
            throw new CustomException(ErrorCode.TEAM_VOTE_ALREADY_EXIST);
        }

        String teamName = voteDTO.getTeamName();
        //팀명 존재여부 체크
        Team team = Team.validateTeamName(teamName);

        Member voter = memberService.findById(voterId);

        //내 팀 아닌지 체크
        if (!validTeam(team, voter)) {
            throw new CustomException(ErrorCode.INVALID_TEAM_VOTE);
        }

        TeamVote teamVote = new TeamVote(team, voter);
        voteRepository.save(teamVote);
    }


    //파트장 투표 결과 조회
    public LeaderResultListDTO getLeaderResult(Part part) {

        //파트가 part인 멤버들 찾아서 리스트에 담기
        List<Member> candidates = memberService.findByPart(part);

        //voteNum 기준 내림차순 정렬
        List<LeaderResultDTO> resultList = candidates.stream()
                .sorted((m1, m2) -> Long.compare(m2.getVoteNum(), m1.getVoteNum()))
                .map(LeaderResultDTO::from)
                .toList();

        return LeaderResultListDTO.from(resultList);
    }


    //팀 투표 결과 조회
    public TeamResultListDTO getTeamResult() {

        Map<Team, Integer> res = new HashMap<>();
        for (Team team : Team.values()) {
            res.put(team, 0);
        }

        List<TeamVote> teamVotes = voteRepository.findAllTeamVotes();
        teamVotes.forEach(vote
                -> res.put(vote.getTeam(), res.get(vote.getTeam()) + 1));

        List<TeamResultDTO> dto = TeamResultDTO.from(res);
        return TeamResultListDTO.from(dto);


    }


    //중복 투표 체크 (파트장)
    private boolean duplicateLeaderVote(Long voterId) {
        LeaderVote vote = voteRepository.findLeaderVoteByVoter_Id(voterId);
        return vote != null;
    }

    //후보자 id 유효성 체크
    private boolean memberExist(Long candidateId) {
        return memberService.existsById(candidateId);
    }

    //파트 체크
    private boolean validPart(Member candidate, Member voter) {
        Part candidatePart = candidate.getPart();
        Part myPart = voter.getPart();

        return candidatePart == myPart;
    }

    //중복 투표 체크 (데모데이)
    private boolean duplicateTeamVote(Long voterId) {
        TeamVote vote = voteRepository.findTeamVoteByVoter_Id(voterId);
        return vote != null;
    }

    //내 팀 아닌지 체크
    private boolean validTeam(Team team, Member voter) {
        return team != voter.getTeam();
    }

}

