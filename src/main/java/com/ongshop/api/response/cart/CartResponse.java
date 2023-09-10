package com.ongshop.api.response.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartResponse {
    private Long cartNo;
    private Long productNo;
    private String title;
    private String imgUrl;
    private String option;
    private int price;
    private int quantity;
}
