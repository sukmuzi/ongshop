package com.ongshop.api.request;

import lombok.Data;

@Data
public class MemberLoginRequestDto {
    private String id;
    private String password;
}
