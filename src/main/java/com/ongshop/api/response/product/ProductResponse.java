package com.ongshop.api.response.product;

import com.ongshop.domain.Product;
import lombok.Data;

@Data
public class ProductResponse {
    private Long no;
    private String title;
    private int price;
    private String category;
    private String[] option;
    private String explanation;
    private String imgUrl;

    public ProductResponse(Product product) {
        this.no = product.getNo();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.option = product.getOption().split(", ");
        this.explanation = product.getExplanation();
        this.imgUrl = product.getImgUrl();
    }
}
