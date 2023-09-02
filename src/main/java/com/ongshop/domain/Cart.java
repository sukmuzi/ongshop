package com.ongshop.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_no")
    private Long no;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no") // FK
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no") // FK
    private Member member;

    private String option;
    private String quantity;

    @Builder
    public Cart(Product product, Member member, String option, String quantity) {
        this.product = product;
        this.member = member;
        this.option = option;
        this.quantity = quantity;
    }
}
