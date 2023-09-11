package com.ongshop.api.request.member;

import com.ongshop.domain.Member;
import com.ongshop.domain.MemberRole;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
public class MemberSignupRequest {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(message = "아이디는 영어 대/소문자, 숫자만 가능합니다.", regexp = "^[a-zA-z0-9]+$")
    @Size(message = "아이디는 최소 4글자 이상, 최대 20글자 이하입니다.", min = 4, max = 20)
    private String id;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(message = "비밀번호는 숫자+영어+특수문자 조합으로 입력해주세요.", regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*+=])(?!.*\\\\s).*$")
    @Size(message = "비밀번호는 최소 8글자 이상, 최대 20글자 이하입니다.", min = 8, max = 20)
    private String password;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(message = "닉네임은 최소 1글자 이상, 최대 10글자 이하입니다.", min = 1, max = 10)
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
