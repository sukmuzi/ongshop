package com.ongshop.api.controller;

import com.ongshop.api.request.MemberRequestDto;
import com.ongshop.api.response.MemberResponseDto;
import com.ongshop.api.service.MemberApiService;
import com.ongshop.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberApiService memberApiService;

    @PostMapping("/api/members")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) throws ApiException {
        return ResponseEntity.ok(memberApiService.signup(memberRequestDto));
    }
}
