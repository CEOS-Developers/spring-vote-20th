package photoground.ceos.vote.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import photoground.ceos.vote.domain.member.dto.JoinRequestDto;
import photoground.ceos.vote.domain.member.service.MemberService;
import photoground.ceos.vote.global.exception.CustomException;
import photoground.ceos.vote.global.exception.ErrorResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<String> joinProcess(@RequestBody JoinRequestDto joinDTO) {
        memberService.joinProcess(joinDTO);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }
}
