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

    private final CartApiRepository cartApiRepository;
    private final ProductApiRepository productApiRepository;
    private final MemberApiRepository memberApiRepository;

    /**
     * 회원 카트 목록 조회
     */
    @GetMapping("/api/carts/{memberNo}")
    public ApiResponse<List<CartResponse>> getCartItems(@PathVariable Long memberNo) {
        List<CartResponse> cartList = cartApiRepository.findAllByMemberNo(memberNo);

        return ApiResponse.createSuccess(cartList);
    }

    /**
     * 카트 등록
     */
    @PostMapping("/api/carts")
    public ApiResponse<?> addCartItem(@RequestBody CartRequest cartRequest) {
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

    /**
     * 카트 수량 변경
     */
    @PutMapping("/api/carts")
    public ApiResponse<?> changeCartItemQuantity(@RequestBody CartRequest cartRequest) {
        Cart cart = cartApiRepository.findById(cartRequest.getCartNo()).get();
        cart.setQuantity(cartRequest.getQuantity());

        cartApiRepository.save(cart);

        return ApiResponse.createSuccessWithNoContent();
    }

    /**
     * 카트 삭제
     */
    @DeleteMapping("/api/carts/{cartNo}")
    public ApiResponse<?> removeCartItem(@PathVariable Long cartNo) {
        cartApiRepository.deleteById(cartNo);

        return ApiResponse.createSuccessWithNoContent();
    }
}
