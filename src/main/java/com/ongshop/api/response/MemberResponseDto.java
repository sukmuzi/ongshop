package com.ongshop.api.response;

import com.ongshop.domain.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberResponseDto {

    private String email;
    private String nickname;

    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }
}
