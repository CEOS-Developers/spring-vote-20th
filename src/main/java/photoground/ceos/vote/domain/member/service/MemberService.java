package photoground.ceos.vote.domain.member.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import photoground.ceos.vote.domain.member.entity.Member;
import photoground.ceos.vote.domain.member.entity.Part;
import photoground.ceos.vote.domain.member.repository.MemberRepository;
import photoground.ceos.vote.global.exception.CustomException;
import photoground.ceos.vote.global.exception.ErrorCode;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> findByPart(Part part){

        return memberRepository.findByPart(part);
    }

    public Member findById(Long candidateId) {
        return memberRepository.findById(candidateId).orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
    }

    public boolean existsById(Long candidateId) {
        return memberRepository.existsById(candidateId);
    }
}
