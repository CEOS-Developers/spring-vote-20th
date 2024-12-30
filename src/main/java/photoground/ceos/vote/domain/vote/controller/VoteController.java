package photoground.ceos.vote.domain.vote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import photoground.ceos.vote.domain.member.entity.Part;
import photoground.ceos.vote.domain.vote.dto.LeaderListDTO;
import photoground.ceos.vote.domain.vote.dto.TeamListDTO;
import photoground.ceos.vote.domain.vote.service.VoteService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/votes")
public class VoteController {

    private final VoteService voteService;

    //파트장 후보 조회
    @GetMapping("/leader")
    public ResponseEntity<LeaderListDTO> showCandidates(@RequestParam Part part){

        LeaderListDTO candidates=voteService.showLeaders(part);
        return ResponseEntity.ok(candidates);
    }

    //팀 후보 조회
    @GetMapping("/team")
    public ResponseEntity<TeamListDTO> showCandidates(){

        TeamListDTO candidates=voteService.showTeams();
        return ResponseEntity.ok(candidates);
    }
}
