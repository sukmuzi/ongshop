package com.ongshop.security;

import com.ongshop.api.repository.MemberApiRepository;
import com.ongshop.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberApiRepository memberApiRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        log.debug("loadUserByUsername id : {}", id);
        Member member = memberApiRepository.findById(id);

        if (member == null) {
            throw new UsernameNotFoundException("존재하지 않는 회원입니다.");
        }

        return new CustomUserDetails(member);
    }
}
