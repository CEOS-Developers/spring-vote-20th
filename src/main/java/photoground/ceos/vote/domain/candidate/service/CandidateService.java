package photoground.ceos.vote.domain.candidate.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import photoground.ceos.vote.domain.candidate.entity.Candidate;
import photoground.ceos.vote.domain.candidate.repository.CandidateRepository;
import photoground.ceos.vote.domain.member.entity.Part;
import photoground.ceos.vote.global.exception.CustomException;
import photoground.ceos.vote.global.exception.ErrorCode;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CandidateService {

    private final CandidateRepository candidateRepository;


    public List<Candidate> findByPart(Part part) {
        return candidateRepository.findByPart(part);
    }

    public boolean existsById(Long candidateId) {
        return candidateRepository.existsById(candidateId);
    }

    public Candidate findById(Long candidateId) {
        return candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CANDIDATE));
    }

}
