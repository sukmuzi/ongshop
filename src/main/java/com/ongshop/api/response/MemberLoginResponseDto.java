package com.ongshop.api.response;

import com.ongshop.domain.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberLoginResponseDto {
    private String id;
    private String nickname;
    private boolean isAdmin;
    private String accessToken;

    public static MemberLoginResponseDto of(Member member, String token) {
        return MemberLoginResponseDto.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .isAdmin(member.getRole().toString().equals("ADMIN"))
                .accessToken(token)
                .build();
    }
}
