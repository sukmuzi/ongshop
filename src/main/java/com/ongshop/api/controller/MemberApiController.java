package com.ongshop.api.controller;

import com.ongshop.api.repository.MemberApiRepository;
import com.ongshop.api.request.MemberLoginRequest;
import com.ongshop.api.request.MemberRequest;
import com.ongshop.api.response.MemberLoginResponse;
import com.ongshop.api.response.MemberResponse;
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
    public MemberLoginResponse signin(@RequestBody MemberLoginRequest memberLoginRequest) {
        Member member = memberApiRepository.findById(memberLoginRequest.getId());
        String token = memberApiService.generateToken(memberLoginRequest);

        return MemberLoginResponse.of(member, token);
    }

    @PostMapping("/api/members")
    public ResponseEntity<MemberResponse> signup(@RequestBody MemberRequest memberRequest) throws ApiException {
        return ResponseEntity.ok(memberApiService.signup(memberRequest));
    }
}
