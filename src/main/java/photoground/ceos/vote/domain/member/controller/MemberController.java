package photoground.ceos.vote.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import photoground.ceos.vote.domain.member.dto.JoinRequestDto;
import photoground.ceos.vote.domain.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public String joinProcess(@RequestBody JoinRequestDto joinDTO) {
        memberService.joinProcess(joinDTO);
        return "회원가입이 완료되었습니다.";
    }

}
