package com.ongshop.api.controller;

import com.ongshop.api.repository.MemberApiRepository;
import com.ongshop.api.request.member.MemberLoginRequest;
import com.ongshop.api.request.member.MemberSignupRequest;
import com.ongshop.api.response.ApiResponse;
import com.ongshop.api.response.member.MemberLoginResponse;
import com.ongshop.api.response.member.MemberSignupResponse;
import com.ongshop.api.service.MemberApiService;
import com.ongshop.domain.Member;
import com.ongshop.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberApiRepository memberApiRepository;
    private final MemberApiService memberApiService;

    @PostMapping("/api/signin")
    public ApiResponse<MemberLoginResponse> signin(@RequestBody MemberLoginRequest memberLoginRequest) {
        Member member = memberApiRepository.findById(memberLoginRequest.getId());
        String token = memberApiService.generateToken(memberLoginRequest);

        return ApiResponse.createSuccess(MemberLoginResponse.of(member, token));
    }

    @PostMapping("/api/members")
    public ApiResponse<?> signup(@RequestBody MemberSignupRequest memberSignupRequest) throws ApiException {
        memberApiService.signup(memberSignupRequest);

        return ApiResponse.createSuccessWithNoContent();
    }
}
