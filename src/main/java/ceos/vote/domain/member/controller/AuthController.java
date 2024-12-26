package ceos.vote.domain.member.controller;

import ceos.vote.domain.member.dto.request.SignupRequestDto;
import ceos.vote.domain.member.dto.response.MemberResponseDto;
import ceos.vote.domain.member.service.AuthService;
import ceos.vote.global.common.response.CommonResponse;
import ceos.vote.global.jwt.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "인증 관련 API")
public class AuthController {

    private final AuthService authService;
    private final JWTUtil jwtUtil;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "회원가입 요청 API")
    public CommonResponse<MemberResponseDto> signup(@Valid @RequestBody SignupRequestDto request) {

        return new CommonResponse<>(authService.signup(request), "회원가입을 성공하였습니다.");
    }

    @PostMapping("/reissue")
    @Operation(summary = "토큰 재발급", description = "토큰 재발급 요청 API")
    public CommonResponse<Void> reissue(HttpServletRequest request, HttpServletResponse response) {

        String refreshToken = authService.extractRefreshToken(request);
        authService.validateRefreshToken(refreshToken);

        String newAccessToken = authService.reissueAccessToken(refreshToken);
        Cookie RefreshTokenCookie = authService.createRefreshTokenCookie(refreshToken);

        authService.setNewTokens(response, newAccessToken, RefreshTokenCookie);

        return new CommonResponse<>("토큰 재발급을 성공하였습니다.");
    }
}
