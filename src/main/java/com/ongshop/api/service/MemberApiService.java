package com.ongshop.api.service;

import com.ongshop.api.repository.MemberApiRepository;
import com.ongshop.api.request.MemberRequestDto;
import com.ongshop.api.response.MemberResponseDto;
import com.ongshop.domain.Member;
import com.ongshop.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberApiService {

    private final PasswordEncoder passwordEncoder;
    private final MemberApiRepository memberApiRepository;

    public MemberResponseDto signup(MemberRequestDto memberRequestDto) throws ApiException {
//        if (memberApiRepository.existsByEmail(memberRequestDto.getEmail())) {
//            throw new RuntimeException("이미 가입되어 있는 이메일입니다.");
//        }

        Member member = memberRequestDto.toMember(passwordEncoder);

        return MemberResponseDto.of(memberApiRepository.save(member));
    }
}
