package com.ongshop.api.controller;

import com.ongshop.api.repository.CartApiRepository;
import com.ongshop.api.repository.MemberApiRepository;
import com.ongshop.api.repository.ProductApiRepository;
import com.ongshop.api.request.cart.CartRequest;
import com.ongshop.api.response.ApiResponse;
import com.ongshop.api.response.cart.CartResponse;
import com.ongshop.api.service.CartApiService;
import com.ongshop.domain.Cart;
import com.ongshop.domain.Member;
import com.ongshop.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CartApiController {

//    private final CartApiService cartApiService;
    private final CartApiRepository cartApiRepository;
    private final ProductApiRepository productApiRepository;
    private final MemberApiRepository memberApiRepository;

    @GetMapping("/api/carts/{memberNo}")
    public ApiResponse<List<CartResponse>> getCarts(@PathVariable Long memberNo) {
        List<CartResponse> cartList = cartApiRepository.findAllByMemberNo(memberNo);

//        List<CartResponse> cartList = carts.stream()
//                .map(c -> new CartResponse(c.getNo(), c.getProduct().getNo(), c.getProduct().getTitle(), c.getProduct().getImgUrl(), c.getOption(), c.getProduct().getPrice(), c.getQuantity()))
//                .collect(Collectors.toList());

        return ApiResponse.createSuccess(cartList);
    }

    @PostMapping("/api/carts")
    public ApiResponse<?> addCarts(@RequestBody CartRequest cartRequest) {
        Cart cart = cartApiRepository.findByProductNoAndMemberNo(cartRequest.getProductNo(), cartRequest.getMemberNo());

        if (cart != null) {
            int prevQuantity = Integer.parseInt(cart.getQuantity());
            int quantity = Integer.parseInt(cartRequest.getQuantity());
            cart.setQuantity(String.valueOf(prevQuantity + quantity));

            cartApiRepository.save(cart);
        } else {
            Product product = productApiRepository.findById(cartRequest.getProductNo()).get();
            Member member = memberApiRepository.findById(cartRequest.getMemberNo()).get();

            Cart newCart = Cart.builder()
                    .product(product)
                    .member(member)
                    .option(cartRequest.getOption())
                    .quantity(cartRequest.getQuantity())
                    .build();

            cartApiRepository.save(newCart);
        }

        return ApiResponse.createSuccessWithNoContent();
    }
}
