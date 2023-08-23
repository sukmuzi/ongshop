package com.ongshop.api.service;

import com.ongshop.api.repository.MemberApiRepository;
import com.ongshop.api.request.MemberLoginRequest;
import com.ongshop.api.request.MemberRequest;
import com.ongshop.api.response.MemberResponse;
import com.ongshop.domain.Member;
import com.ongshop.exception.ApiException;
import com.ongshop.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberApiService {

    private final PasswordEncoder passwordEncoder;
    private final MemberApiRepository memberApiRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

//    @Transactional
//    public TokenDto signin(String id, String password) {
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, password);
//        log.debug("signin >>>> id : {}, password: {}", id, password);
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//
////        TokenDto tokenDto = jwtTokenProvider.generateToken(authentication);
//////        return tokenDto;
//        String token = jwtTokenProvider.generateToken(authentication);
//    }

    public String generateToken(MemberLoginRequest memberLoginRequest) {
        String id = memberLoginRequest.getId();
        String password = memberLoginRequest.getPassword();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, password);
        log.debug("signin >>>> id : {}, password: {}", id, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return jwtTokenProvider.generateToken(authentication);
    }

    public MemberResponse signup(MemberRequest memberRequest) throws ApiException {
//        if (memberApiRepository.existsByEmail(memberRequestDto.getEmail())) {
//            throw new RuntimeException("이미 가입되어 있는 이메일입니다.");
//        }

        Member member = memberRequest.toMember(passwordEncoder);

        return MemberResponse.of(memberApiRepository.save(member));
    }
}
