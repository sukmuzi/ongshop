package com.ongshop.api.request.member;

import lombok.Data;

@Data
public class MemberLoginRequest {
    private String id;
    private String password;
}
