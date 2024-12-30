package ceos.vote.domain.vote.controller;

import ceos.vote.domain.member.entity.Member;
import ceos.vote.domain.vote.dto.response.DeveloperListResponseDto;
import ceos.vote.domain.vote.dto.response.DeveloperVoteResultResponseDto;
import ceos.vote.domain.vote.dto.response.TeamListResponseDto;
import ceos.vote.domain.vote.dto.response.TeamVoteResultResponseDto;
import ceos.vote.domain.vote.service.VoteService;
import ceos.vote.global.annotation.Login;
import ceos.vote.global.common.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vote")
@RequiredArgsConstructor
@Tag(name = "Vote", description = "투표 관련 API")
public class VoteController {

    private final VoteService voteService;


    @GetMapping("/developer")
    @Operation(summary = "개발자 리스트 조회하는 API", description = "request param으로 type=backend 또는 type=frontend 를 넘겨주세요")
    public CommonResponse<List<DeveloperListResponseDto>> getDeveloperList(@RequestParam String type) {

        return new CommonResponse<>(voteService.getDeveloperList(type), "개발자 리스트 조회 완료");
    }

    @GetMapping("/team")
    @Operation(summary = "팀 리스트 조회하는 API", description = "-")
    public CommonResponse<List<TeamListResponseDto>> getDeveloperList() {

        return new CommonResponse<>(voteService.getTeamList(), "팀 리스트 조회 완료");
    }

    @PostMapping("/developer/{memberId}")
    @Operation(summary = "개발자 파트장 투표하는 API", description = "pathvariable로 투표하고자 하는 멤버의 id를 넘겨주세요")
    public CommonResponse<Void> voteDeveloper(@PathVariable Long memberId, @Login Member loginMember) {

        voteService.voteDeveloper(memberId, loginMember);
        return new CommonResponse<>("파트장 투표 완료");
    }

    @PostMapping("/team/{teamId}")
    @Operation(summary = "팀 투표하는 API", description = "pathvariable로 투표하고자 하는 팀의 id를 넘겨주세요")
    public CommonResponse<Void> voteTeam(@PathVariable Long teamId, @Login Member loginMember) {

        voteService.voteTeam(teamId, loginMember);
        return new CommonResponse<>("팀 투표 완료");
    }

    @GetMapping("/developer/result")
    @Operation(summary = "파트장 투표 결과 조회하는 API", description = "request param으로 type=backend 또는 type=frontend 를 넘겨주세요")
    public CommonResponse<List<DeveloperVoteResultResponseDto>> getDeveloperVoteResult(@RequestParam String type) {

        return new CommonResponse<>(voteService.getDeveloperVoteResult(type), "파트장 투표 결과 조회 완료");
    }

    @GetMapping("/team/result")
    @Operation(summary = "팀 투표 결과 조회하는 API", description = "-")
    public CommonResponse<List<TeamVoteResultResponseDto>> getTeamVoteResult() {

        return new CommonResponse<>(voteService.getTeamVoteResult(), "팀 투표 결과 조회 완료");
    }
}
