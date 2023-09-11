package com.ongshop.api.service;

import com.ongshop.api.repository.MemberApiRepository;
import com.ongshop.api.request.member.MemberLoginRequest;
import com.ongshop.api.request.member.MemberSignupRequest;
import com.ongshop.api.response.member.MemberSignupResponse;
import com.ongshop.domain.Member;
import com.ongshop.exception.ApiException;
import com.ongshop.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberApiService {

    private final PasswordEncoder passwordEncoder;
    private final MemberApiRepository memberApiRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;

    public String generateToken(MemberLoginRequest memberLoginRequest) {
        String id = memberLoginRequest.getId();
        String password = memberLoginRequest.getPassword();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, password);
        log.debug("signin >>>> id : {}, password: {}", id, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        String token = jwtTokenProvider.generateToken(authentication);

//        redisTemplate.opsForValue().set("RT:" + id, token, jwtTokenProvider.getExpiration(token));

        return token;
    }

    public void signup(MemberSignupRequest memberSignupRequest) {
        validateDuplicateMember(memberSignupRequest);
        Member member = memberSignupRequest.toMember(passwordEncoder);

        MemberSignupResponse.of(memberApiRepository.save(member));
    }

    private void validateDuplicateMember(MemberSignupRequest memberSignupRequest) {
        Member findMembers = memberApiRepository.findById(memberSignupRequest.getId());

        if(findMembers != null) {
            throw new IllegalStateException("아이디가 이미 사용 중 입니다.");
        }
    }
}
