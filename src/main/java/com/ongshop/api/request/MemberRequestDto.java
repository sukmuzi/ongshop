package com.ongshop.api.request;

import com.ongshop.domain.Member;
import com.ongshop.domain.MemberRole;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Builder
public class MemberRequestDto {
    private String id;
    private String password;
    private String nickname;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .id(id)
                .nickname(nickname)
                .password(passwordEncoder.encode(password))
                .role(MemberRole.USER)
                .build();
    }
}
