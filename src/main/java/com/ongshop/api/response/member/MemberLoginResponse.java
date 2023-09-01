package com.ongshop.api.response.member;

import com.ongshop.domain.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberLoginResponse {
    private Long memberNo;
    private String id;
    private String nickname;
    private boolean isAdmin;
    private String accessToken;

    public static MemberLoginResponse of(Member member, String token) {
        return MemberLoginResponse.builder()
                .memberNo(member.getNo())
                .id(member.getId())
                .nickname(member.getNickname())
                .isAdmin(member.getRole().toString().equals("ADMIN"))
                .accessToken(token)
                .build();
    }
}
