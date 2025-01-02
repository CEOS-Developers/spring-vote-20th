package ceos.vote.domain.member.service;

import ceos.vote.domain.member.dto.response.MemberResponseDto;
import ceos.vote.domain.member.entity.Member;
import ceos.vote.global.exception.ApplicationException;
import ceos.vote.global.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static ceos.vote.global.exception.ExceptionCode.NOT_FOUND_USER;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new ApplicationException(NOT_FOUND_USER));
    }

    // [GET] 회원 기본 정보 조회
    public MemberResponseDto getMemberInfo(Member loginMember) {

        return MemberResponseDto.from(loginMember);
    }
}
