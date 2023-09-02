package com.ongshop.api.request.cart;

import lombok.Data;

@Data
public class CartRequest {
    private Long memberNo;
    private Long productNo;
    private String option;
    private String quantity;
}
