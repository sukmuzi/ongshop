package com.ongshop.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_no")
    private Long no;

    private String title;
    private int price;
    private String category;
    private String option;
    private String explanation;
    private String imgUrl;

    @Builder
    public Product(String title, int price, String category, String option, String explanation, String imgUrl) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.option = option;
        this.explanation = explanation;
        this.imgUrl = imgUrl;
    }
}
