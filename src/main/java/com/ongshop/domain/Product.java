package com.ongshop.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue
    private Long no;

    private String title;
    private int price;
    private String explanation;
    private String imgUrl;

    @Builder
    public Product(String title, int price, String explanation, String imgUrl) {
        this.title = title;
        this.price = price;
        this.explanation = explanation;
        this.imgUrl = imgUrl;
    }
}
