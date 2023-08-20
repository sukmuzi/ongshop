package com.ongshop.dto;

import com.ongshop.domain.Member;
import lombok.Data;

@Data
public class MemberDto {
    private String email;
    private String password;
    private String nickname;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
    }
}
