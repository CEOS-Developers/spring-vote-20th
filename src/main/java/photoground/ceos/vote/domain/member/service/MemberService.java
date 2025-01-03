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

        boolean isExist = memberRepository.existsByUsername(username);

        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        if (isExist) {
            throw new IllegalStateException("Username already exists");
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
