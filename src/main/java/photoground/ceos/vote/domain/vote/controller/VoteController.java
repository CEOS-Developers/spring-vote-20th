package photoground.ceos.vote.domain.vote.controller;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import photoground.ceos.vote.domain.member.entity.Part;
import photoground.ceos.vote.domain.vote.dto.LeaderListDTO;
import photoground.ceos.vote.domain.vote.dto.LeaderResultListDTO;
import photoground.ceos.vote.domain.vote.dto.TeamListDTO;
import photoground.ceos.vote.domain.vote.dto.TeamResultListDTO;
import photoground.ceos.vote.domain.vote.dto.VoteLeaderDTO;
import photoground.ceos.vote.domain.vote.dto.VoteTeamDTO;
import photoground.ceos.vote.domain.vote.service.VoteService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/votes")
public class VoteController {

    private final VoteService voteService;

    //파트장 후보 조회
    @GetMapping("/leader")
    public ResponseEntity<LeaderListDTO> showCandidates(@RequestParam Part part) {

        LeaderListDTO candidates = voteService.showLeaders(part);
        return ResponseEntity.ok(candidates);
    }

    //팀 후보 조회
    @GetMapping("/team")
    public ResponseEntity<TeamListDTO> showCandidates() {

        TeamListDTO candidates = voteService.showTeams();
        return ResponseEntity.ok(candidates);
    }

    //파트장 투표
    @PostMapping("/leader") //로그인 구현 후 수정
    public ResponseEntity<Map<String, String>> LeaderVote(@RequestBody VoteLeaderDTO voteDTO,
                                                          @RequestParam Long voterId) {

        voteService.voteLeader(voteDTO, voterId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "파트장 투표가 완료되었습니다.");
        return ResponseEntity.ok(response);
    }

    //팀 투표
    @PostMapping("/team")
    public ResponseEntity<Map<String, String>> TeamVote(@RequestBody VoteTeamDTO voteDTO, @RequestParam Long voterId) {

        voteService.voteTeam(voteDTO, voterId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "데모데이 투표가 완료되었습니다.");
        return ResponseEntity.ok(response);
    }

    //파트장 투표 결과 조회
    @GetMapping("/leader/result")
    public ResponseEntity<LeaderResultListDTO> getLeaderResult(@RequestParam Part part) {

        LeaderResultListDTO result = voteService.getLeaderResult(part);
        return ResponseEntity.ok(result);
    }

    //팀 투표 결과 조회
    @GetMapping("/team/result")
    public ResponseEntity<TeamResultListDTO> getTeamResult() {

        TeamResultListDTO result = voteService.getTeamResult();
        return ResponseEntity.ok(result);
    }
}
