package photoground.ceos.vote.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import photoground.ceos.vote.domain.member.dto.JoinRequestDto;
import photoground.ceos.vote.domain.member.entity.Member;
import photoground.ceos.vote.domain.member.entity.UserRole;
import photoground.ceos.vote.domain.member.repository.MemberRepository;
import photoground.ceos.vote.global.exception.CustomException;
import photoground.ceos.vote.global.exception.ErrorCode;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void joinProcess(JoinRequestDto joinDTO) {

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();
        String email = joinDTO.getEmail();

        // Username 중복 확인
        boolean isUsernameExist = memberRepository.existsByUsername(username);
        if (isUsernameExist) {
            throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
        }

        // Email 중복 확인
        boolean isEmailExist = memberRepository.existsByEmail(email);
        if (isEmailExist) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        // 비밀번호 null 확인
        if (password == null) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        // 기본 유저 권한은 USER로 설정
        Member member = joinDTO.toEntity(bCryptPasswordEncoder.encode(password), UserRole.USER);
        memberRepository.save(member);
    }


    public Member findById(Long candidateId) {
        return memberRepository.findById(candidateId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
    }


}
