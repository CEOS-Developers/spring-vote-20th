package ceos.vote.domain.member.service;

import ceos.vote.domain.member.dto.request.SignupRequestDto;
import ceos.vote.domain.member.dto.response.MemberResponseDto;
import ceos.vote.domain.member.entity.Member;
import ceos.vote.domain.member.entity.PartType;
import ceos.vote.domain.member.entity.Refresh;
import ceos.vote.domain.member.entity.TeamType;
import ceos.vote.global.exception.ApplicationException;
import ceos.vote.global.jwt.JWTUtil;
import ceos.vote.global.repository.MemberRepository;
import ceos.vote.global.repository.RefreshRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static ceos.vote.global.exception.ExceptionCode.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    // [POST] 회원가입
    @Transactional
    public MemberResponseDto signup(SignupRequestDto request) {

        String userId = request.userId();
        String password = request.password();

        if(memberRepository.existsMemberByUserId(userId)){
            throw new ApplicationException(DUPLICATED_USER_ID);
        }

        PartType part;
        TeamType team;

        if (request.part().equals("프론트엔드")) {
            part = PartType.FRONTEND;
        } else if (request.part().equals("백엔드")) {
            part = PartType.BACKEND;
        } else {
            throw new ApplicationException(INVALID_PART_TYPE);
        }

        if (request.team().equals("포토그라운드")) {
            team = TeamType.PHOTOGROUND;
        } else if (request.team().equals("엔젤브릿지")) {
            team = TeamType.ANGELBRIDGE;
        } else if (request.team().equals("페달지니")) {
            team = TeamType.PEDALGENIE;
        } else if (request.team().equals("케이크웨이")) {
            team = TeamType.CAKEWAY;
        } else if (request.team().equals("커피딜")) {
            team = TeamType.COFFEEDEAL;
        } else {
            throw new ApplicationException(INVALID_TEAM_TYPE);
        }

        Member newMember = request.toEntity(bCryptPasswordEncoder.encode(password), part, team);

        memberRepository.save(newMember);

        return MemberResponseDto.from(newMember);
    }

    /**
     * Refresh Token 추출
     * **/
    public String extractRefreshToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refreshToken")) {
                    return cookie.getValue();
                }
            }
        }
        throw new ApplicationException(NOT_FOUND_REFRESH_TOKEN);
    }

    /**
     * Refresh Token 검증
     * **/
    public void validateRefreshToken(String refreshToken) {

        try {
            jwtUtil.isExpired(refreshToken);
        } catch (ExpiredJwtException e) {
            throw new ApplicationException(EXPIRED_PERIOD_REFRESH_TOKEN);
        }

        String category = jwtUtil.getCategory(refreshToken);
        if (!category.equals("refresh")) {
            throw new ApplicationException(INVALID_REFRESH_TOKEN);
        }

        Boolean isExist = refreshRepository.existsByRefresh(refreshToken);
        if (!isExist) {
            throw new ApplicationException(INVALID_REFRESH_TOKEN);
        }
    }

    /**
     * Access Token 재발급
     * **/
    public String reissueAccessToken(String refreshToken) {

        String userId = jwtUtil.getUsername(refreshToken);
        String role = jwtUtil.getRole(refreshToken);
        return jwtUtil.createJwt("access", userId, role, 1000L * 60 * 60 * 24 * 14); // 2주 (임시)
    }

    /**
     * 새로운 Refresh Token 생성
     * **/
    @Transactional
    public Cookie createRefreshTokenCookie(String refreshToken) {

        String userId = jwtUtil.getUsername(refreshToken);
        String role = jwtUtil.getRole(refreshToken);
        String newRefresh = jwtUtil.createJwt("refresh", userId, role, 1000L * 60 * 60 * 24 * 14);

        if (userId == null) {
            throw new ApplicationException(FAIL_TO_VALIDATE_TOKEN);
        }

        deleteAndSaveNewRefreshToken(userId, newRefresh, 1000L * 60 * 60 * 24 * 14);

        return createCookie("refreshToken", newRefresh);
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 24 * 14);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        // cookie.setSecure(true);

        return cookie;
    }

    /**
     * 기존의 Refresh Token 삭제 후 새 Refresh Token 저장
     **/
    @Transactional
    public void deleteAndSaveNewRefreshToken(String userId, String newRefresh, Long expiredMs) {

        refreshRepository.deleteByUserId(userId);

        addRefreshEntity(userId, newRefresh, expiredMs);
    }

    /**
     * 새로운 Refresh Token 저장하는 메서드
     **/
    @Transactional
    public void addRefreshEntity(String userId, String refresh, Long expiredMs) {

        Date expirationDate = new Date(System.currentTimeMillis() + expiredMs);

        Refresh refreshEntity = Refresh.builder()
                .userId(userId)
                .refresh(refresh)
                .expiration(expirationDate.toString())
                .build();

        refreshRepository.save(refreshEntity);
    }

    /**
     * 응답 헤더 및 쿠키 설정
     * **/
    public void setNewTokens(HttpServletResponse response, String newAccessToken, Cookie refreshCookie) {

        response.setHeader("Authorization", "Bearer " + newAccessToken);

        response.addCookie(refreshCookie);
    }
}
