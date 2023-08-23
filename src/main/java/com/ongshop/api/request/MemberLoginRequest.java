package com.ongshop.api.request;

import lombok.Data;

@Data
public class MemberLoginRequest {
    private String id;
    private String password;
}
