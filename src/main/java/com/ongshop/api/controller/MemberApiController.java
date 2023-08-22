package com.ongshop.api.controller;

import com.ongshop.api.repository.MemberApiRepository;
import com.ongshop.api.request.MemberLoginRequestDto;
import com.ongshop.api.request.MemberRequestDto;
import com.ongshop.api.response.MemberLoginResponseDto;
import com.ongshop.api.response.MemberResponseDto;
import com.ongshop.api.response.TokenDto;
import com.ongshop.api.service.MemberApiService;
import com.ongshop.domain.Member;
import com.ongshop.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberApiRepository memberApiRepository;
    private final MemberApiService memberApiService;

    @PostMapping("/api/signin")
    public MemberLoginResponseDto signin(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        Member member = memberApiRepository.findById(memberLoginRequestDto.getId());
        String token = memberApiService.generateToken(memberLoginRequestDto);

        return MemberLoginResponseDto.of(member, token);
    }

    @PostMapping("/api/members")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) throws ApiException {
        return ResponseEntity.ok(memberApiService.signup(memberRequestDto));
    }
}
