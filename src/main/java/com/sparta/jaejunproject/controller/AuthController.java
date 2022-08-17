package com.sparta.jaejunproject.controller;

import com.sparta.jaejunproject.dto.MemberRequestDto;
import com.sparta.jaejunproject.dto.MemberResponseDto;
import com.sparta.jaejunproject.dto.TokenDto;
import com.sparta.jaejunproject.dto.TokenRequestDto;
import com.sparta.jaejunproject.model.Member;
import com.sparta.jaejunproject.repository.MemberRepository;
import com.sparta.jaejunproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/api/member")
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final MemberRepository memberRepository;



    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @PostMapping("/login")
    public MemberResponseDto login(@RequestBody MemberRequestDto memberRequestDto, HttpServletResponse response) {
        TokenDto tokenDto = authService.login(memberRequestDto);
        response.setHeader("Authorization", "Bearer " + tokenDto.getAccessToken());
        response.setHeader("Refresh-Token", tokenDto.getRefreshToken());
        response.setHeader("Access-Token-Expire-Time", String.valueOf(tokenDto.getAccessTokenExpiresIn()));
        Member member = memberRepository.findByUserId(memberRequestDto.getNickname()).get();
        return MemberResponseDto.of(member);

    }
    @PostMapping("/idCheck")
    public Boolean idCheck(@RequestBody MemberRequestDto memberRequestDto) {
        return authService.idCheck(memberRequestDto);
    }
    @PostMapping("/reissue")  //재발급을 위한 로직
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}