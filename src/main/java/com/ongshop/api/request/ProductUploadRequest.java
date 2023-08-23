package com.ongshop.api.request;

import com.ongshop.domain.Member;
import com.ongshop.domain.MemberRole;
import com.ongshop.domain.Product;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class ProductUploadRequest {
    private String title;
    private int price;
    private String category;
    private String option;
    private String explanation;
    private MultipartFile file;

    public Product toProduct(String imgUrl) {
        return Product.builder()
                .title(title)
                .price(price)
                .explanation(explanation)
                .imgUrl(imgUrl)
                .build();
    }
}
