package ceos.vote.domain.member.controller;

import ceos.vote.domain.member.dto.CustomUserDetails;
import ceos.vote.domain.member.dto.response.MemberResponseDto;
import ceos.vote.domain.member.entity.Member;
import ceos.vote.domain.member.service.MemberService;
import ceos.vote.global.annotation.Login;
import ceos.vote.global.common.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@Tag(name = "Member", description = "회원 관련 API")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원 기본 정보 조회", description = "회원의 기본 정보를 조회하는 API")
    @GetMapping
    public CommonResponse<MemberResponseDto> getMemberInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {

        Long memberId = userDetails.getMemberId();

        return new CommonResponse<>(memberService.getMemberInfo(memberId), "회원 기본 정보 조회를 성공하였습니다.");
    }
}
