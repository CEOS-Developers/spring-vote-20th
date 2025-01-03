package photoground.ceos.vote.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Auth
    BAD_CREDENTIALS(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 잘못되었습니다."),
    FAIL_AUTHENTICATION(HttpStatus.UNAUTHORIZED, "로그인에 실패했습니다. 다시 시도해주세요."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
    FAIL_AUTHORIZATION(HttpStatus.FORBIDDEN, "권한이 없는 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL SERVER ERROR"),
    DUPLICATE_USERNAME(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디입니다."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호는 반드시 필요합니다."),

    // Vote
    LEADER_VOTE_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "이미 투표하셨습니다."),
    LEADER_NOT_EXIST(HttpStatus.NOT_FOUND, "해당 후보자는 존재하지 않습니다."),
    INVALID_PART_VOTE(HttpStatus.BAD_REQUEST, "본인의 파트에 해당하는 파트장 투표만 할 수 있습니다."),
    NOT_FOUND_TEAM_NAME(HttpStatus.NOT_FOUND, "해당 팀명은 존재하지 않습니다."),
    TEAM_VOTE_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "이미 투표하셨습니다."),
    INVALID_TEAM_VOTE(HttpStatus.BAD_REQUEST, "본인 팀에는 투표할 수 없습니다."),

    // CANDIDATE
    NOT_FOUND_CANDIDATE(HttpStatus.NOT_FOUND, "해당 id의 후보자는 존재하지 않습니다."),

    // MEMBER
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "해당 id의 사용자는 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;

}
