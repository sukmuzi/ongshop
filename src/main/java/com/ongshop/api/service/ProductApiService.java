package com.ongshop.api.service;

import com.ongshop.api.repository.ProductApiRepository;
import com.ongshop.api.response.product.ProductListResponse;
import com.ongshop.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductApiService {

    private final ProductApiRepository productApiRepository;

    public List<ProductListResponse> getProductList(Long lastProductNo, int size) {
        PageRequest pageRequest = PageRequest.of(0, size);
        Page<Product> entityPage = productApiRepository.findByProductNoBiggerThanOrderByNo(lastProductNo, pageRequest);
        List<Product> entityList = entityPage.getContent();

        return entityList.stream()
                .map(ProductListResponse::new)
                .collect(Collectors.toList());
    }
}
