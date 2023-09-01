package com.ongshop.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long no;

    @Column(unique = true)
    private String id;

    @Column(unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

//    @OneToMany(mappedBy = "cart")
//    private List<Cart> carts = new ArrayList<>();

    @Builder
    public Member(String id, String nickname, String password, MemberRole role) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
    }
}
