package photoground.ceos.vote.domain.member.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import photoground.ceos.vote.domain.member.entity.Member;
import photoground.ceos.vote.domain.member.entity.Part;
import photoground.ceos.vote.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> findByPart(Part part){

        return memberRepository.findByPart(part);
    }
}
