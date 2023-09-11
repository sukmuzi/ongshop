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
import com.ongshop.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberApiRepository memberApiRepository;
    private final MemberApiService memberApiService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/api/signin")
    public ApiResponse<MemberLoginResponse> signin(@RequestBody MemberLoginRequest memberLoginRequest) {
        Member member = memberApiRepository.findById(memberLoginRequest.getId());
        String token = memberApiService.generateToken(memberLoginRequest);

        return ApiResponse.createSuccess(MemberLoginResponse.of(member, token));
    }

    @PostMapping("/api/members")
    public ApiResponse<?> signup(@Valid @RequestBody MemberSignupRequest memberSignupRequest) throws Exception {
        memberApiService.signup(memberSignupRequest);

        return ApiResponse.createSuccessWithNoContent();
    }

    @GetMapping("/api/members/{id}/exists")
    public ApiResponse<?> isExistMember(@PathVariable String id) {
        boolean isExist = memberApiRepository.existsById(id);
        String message = isExist ? "이미 사용 중인 아이디 입니다." : "사용 가능한 아이디 입니다.";

        return ApiResponse.createSuccessWithMessage(isExist, message);
    }

    @PostMapping("/api/logout")
    public ApiResponse<?> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
//        long expiration = jwtTokenProvider.getExpiration(token);
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("/api/logout token : {}", token);
        log.info("/api/logout id : {}", id);

        if (redisTemplate.opsForValue().get("RT:" + id) != null) {
            redisTemplate.delete("RT:" + id);
        }

        return ApiResponse.createSuccessWithNoContent();
    }
}
