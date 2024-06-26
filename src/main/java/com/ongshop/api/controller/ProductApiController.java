package com.ongshop.api.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ongshop.api.repository.ProductApiRepository;
import com.ongshop.api.request.product.ProductUploadRequest;
import com.ongshop.api.response.ApiResponse;
import com.ongshop.api.response.product.ProductResponse;
import com.ongshop.api.service.ProductApiService;
import com.ongshop.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductApiController {

    private final Cloudinary cloudinary;
    private final ProductApiService productApiService;

    private final ProductApiRepository productApiRepository;

    @PostMapping("/api/products")
    public ApiResponse<?> uploadProducts(ProductUploadRequest productUploadRequest) throws Exception {
        Map uploadResult = cloudinary.uploader().upload(productUploadRequest.getFile().getBytes(), ObjectUtils.emptyMap());
        String publicId = uploadResult.get("public_id").toString();
        String imgUrl = uploadResult.get("url").toString();
        log.info("uploaded the file : {}, img url : {}", publicId, imgUrl);

        Product product = productUploadRequest.toProduct(imgUrl);
        productApiRepository.save(product);

        return ApiResponse.createSuccessWithNoContent();
    }

    @GetMapping("/api/products")
    public ApiResponse<List<ProductResponse>> getProductsList(@RequestParam Long lastProductNo, @RequestParam int size) throws Exception {
        List<ProductResponse> productList = productApiService.getProductList(lastProductNo, size);

        return ApiResponse.createSuccess(productList);
    }

    @GetMapping("/api/products/{no}")
    public ApiResponse<ProductResponse> getProductDetail(@PathVariable Long no) {
        ProductResponse productResponse = productApiService.getProductDetail(no);

        return ApiResponse.createSuccess(productResponse);
    }
}
