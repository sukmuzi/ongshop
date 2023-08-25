package com.ongshop.api.response.member;

import com.ongshop.domain.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberSignupResponse {

    private String id;
    private String nickname;

    public static MemberSignupResponse of(Member member) {
        return MemberSignupResponse.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .build();
    }
}
