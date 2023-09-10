package com.ongshop.api.request.product;

import com.ongshop.domain.Product;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class ProductUploadRequest {
    private String title;
    private String price;
    private String category;
    private String option;
    private String explanation;
    private MultipartFile file;

    public Product toProduct(String imgUrl) {
        return Product.builder()
                .title(title)
                .price(Integer.parseInt(price))
                .category(category)
                .option(option)
                .explanation(explanation)
                .imgUrl(imgUrl)
                .build();
    }
}
