package ceos.vote.domain.vote.service;

import ceos.vote.domain.member.entity.Member;
import ceos.vote.domain.member.entity.PartType;
import ceos.vote.domain.team.entity.Team;
import ceos.vote.domain.vote.dto.response.DeveloperListResponseDto;
import ceos.vote.domain.vote.dto.response.TeamListResponseDto;
import ceos.vote.domain.vote.dto.response.DeveloperVoteResultResponseDto;
import ceos.vote.domain.vote.dto.response.TeamVoteResultResponseDto;
import ceos.vote.global.exception.ApplicationException;
import ceos.vote.global.repository.MemberRepository;
import ceos.vote.global.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static ceos.vote.global.exception.ExceptionCode.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VoteService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    // [GET] 개발자 리스트 조회
    public List<DeveloperListResponseDto> getDeveloperList(String type) {

        PartType partType = PartType.valueOf(type.toUpperCase());
        List<Member> members = memberRepository.findByPartOrderByCountDesc(partType);

        return members.stream()
                .map(DeveloperListResponseDto::from)
                .collect(Collectors.toList());
    }

    // [GET] 팀 리스트 조회
    public List<TeamListResponseDto> getTeamList() {

        List<Team> teams = teamRepository.findAll();
        return teams.stream()
                .map(TeamListResponseDto::from)
                .collect(Collectors.toList());

    }

    // [PATCH] 개발자 파트장 투표
    @Transactional
    public void voteDeveloper(Long memberId, Member loginMember) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ApplicationException(NOT_FOUND_USER));
        // 나 자신에게 투표할 수 없음
        if (member == loginMember)
            throw new ApplicationException(BAD_REQUEST_SELF);
        // 같은 파트에게만 투표할 수 있음
        if (member.getPart() != loginMember.getPart())
            throw new ApplicationException(BAD_REQUEST_DEVELOPER);
        // 이미 투표했다면 다시 투표할 수 없음
        PartType type = member.getPart();
        if (type.equals(PartType.BACKEND)) {
            if (loginMember.isVoteBack())
                throw new ApplicationException(ALREADY_VOTE_DEVELOPER);
            loginMember.voteToBack();
        }
        else if (type.equals(PartType.FRONTEND)) {
            if (loginMember.isVoteFront())
                throw new ApplicationException(ALREADY_VOTE_DEVELOPER);
            loginMember.voteToFront();
        }
        member.voteToMe();
        memberRepository.save(member);
        memberRepository.save(loginMember);
    }

    // [PATCH] 팀 투표
    @Transactional
    public void voteTeam(Long teamId, Member loginMember) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ApplicationException(INVALID_TEAM_TYPE));
        // 같은 팀에게 투표할 수 없음
        if (loginMember.getTeam() == team)
            throw new ApplicationException(BAD_REQUEST_TEAM);
        // 이미 투표했으면 다시 투표할 수 없음
        if (loginMember.isVoteTeam())
            throw new ApplicationException(ALREADY_VOTE_TEAM);

        team.voteToMe();
        teamRepository.save(team);
        loginMember.voteToTeam();
        memberRepository.save(loginMember);
    }

    // [GET] 개발자 파트장 투표 결과 조회
    public List<DeveloperVoteResultResponseDto> getDeveloperVoteResult(String type) {
        PartType partType = PartType.valueOf(type.toUpperCase());
        List<Member> members = memberRepository.findByPartOrderByCountDesc(partType);

        return members.stream()
                .map(DeveloperVoteResultResponseDto::from)
                .collect(Collectors.toList());
    }

    // [GET] 팀 투표 결과 조회
    public List<TeamVoteResultResponseDto> getTeamVoteResult() {

        List<Team> teams = teamRepository.findAllByOrderByCountDesc();
        return teams.stream()
                .map(TeamVoteResultResponseDto::from)
                .collect(Collectors.toList());
    }
}
